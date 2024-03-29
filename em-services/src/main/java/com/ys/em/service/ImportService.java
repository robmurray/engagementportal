package com.ys.em.service;

import com.ys.common.service.ServicesBase;
import com.ys.em.infra.domain.*;
import com.ys.em.infra.repository.*;
import com.ys.em.service.converter.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by rob on 4/22/15.
 */
@Service
public class ImportService extends ServicesBase {
    public static final String DEFAULT_MODEL_GROUPS = "VPN,PNA,CSN,WLAN,ASPRO";

    private static Logger logger = LoggerFactory.getLogger(PortalService.class);

    private boolean addTestData = true;

    @Autowired
    private ImportOracleOBIStageRepository oracleOBIStageRepository;

    @Autowired
    private ImportOracleOBIRepository oracleOBIRepository;

    @Autowired
    private ImportControlRepository importControlRepository;

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PortalService portalService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ImportMasterRepository importMasterRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private ProjectResourceRepository projectResourceRepository;

    @Autowired
    private ProjectActivityRepository projectActivityRepository;


    @Autowired
    private ProjectNotesRepository projectNotesRepository;

    @Autowired
    private SOImportMapper soImportMapper;

    public ConversionResults<ImportOracleObiStage> importOracleOBICSVSalesOrder(MultipartFile file) throws CSVConversionFailureException {
        ImportControlEntity ice = new ImportControlEntity();

        if (file == null || file.isEmpty()) {
            // nothing to do
            logger.info("csv file is empty. nothing to do");

            ice.setFileName("n/a");
            ice.setStatus(ImportControlStatus.NOTHING2DO);
            importControlRepository.save(ice);

            return new ConversionResults();
        }
        InputStream is = null;
        try {
            is = file.getInputStream();
        } catch (IOException e) {
            throw new CSVConversionFailureException("error aquiring input stream", e);
        }

        return this.importOracleOBICSVSalesOrder(is, DEFAULT_MODEL_GROUPS, file.getName());
    }

    public ConversionResults<ImportOracleObiStage> importOracleOBICSVSalesOrder(InputStream inputStream, String targetModelGroups, String fileName) throws CSVConversionFailureException {

        ConversionResults<ImportOracleObiStage> results = null;
        OracleOBISalesOrderCSVImport salesOrderConverter = new OracleOBISalesOrderCSVImport();
        ImportControlEntity ice = new ImportControlEntity();

        try {
            // step 0 create import Control record

            // update import control
            ice.setFileName(fileName);
            ice.setStatus(ImportControlStatus.BEGIN);
            ice.setEncoding(salesOrderConverter.getEncoding());

            importControlRepository.save(ice);


            // set the import control id
            salesOrderConverter.setImportControlId(ice.getImportControlId());
            // step 1 csv to staging

            try {

                // copnvert to csv
                results = salesOrderConverter.convert(inputStream);
                results.setBatchId(ice.getImportControlId());


            } catch (Exception e) {
                ice.setStatus(ImportControlStatus.ERROR);
                importControlRepository.save(ice);
                throw e;

            }

            // update import control
            ice.setStatus(ImportControlStatus.CSV2STAGING_START);
            ice.setNumberOfRecords(results.getNumRecordsToProcess());
            importControlRepository.save(ice);


            if (results != null && results.getNumRecordsProcessed() > 0) {
                this.oracleOBIStageRepository.save(results.getConvertedRecords());
            }

            // update import control
            ice.setStatus(ImportControlStatus.CSV2STAGING_COMPLETE);
            importControlRepository.save(ice);

            // step 2 staging to import
            List<ImportOracleObiStage> wrkList = this.oracleOBIStageRepository.findByImportControlId(ice.getImportControlId());
            HashSet<ImportOracleObiEntity> resultList = new HashSet<ImportOracleObiEntity>();


            for (ImportOracleObiStage stage : wrkList) {

                try {


                        if (stage == null || stage.getModelGroupCode() == null || (targetModelGroups != null && !targetModelGroups.contains(stage.getModelGroupCode()))) {
                        // only import model groups that we care about
                        continue;
                    }

                    ImportOracleObiEntity entity = new ImportOracleObiEntity();

                    // ok to store new object
                    ConversionUtils.convertToDate(stage.getActivityDate(), Constants.DEFAULT_DATE_FORMAT);

                    entity.setActivityDate(ConversionUtils.convertToDate(stage.getActivityDate(), Constants.DEFAULT_DATE_FORMAT));
                    entity.setActivityMonth(ConversionUtils.convertToInt(stage.getActivityMonth()));
                    entity.setActivityYear(ConversionUtils.convertToInt(stage.getActivityYear()));
                    entity.setBtCustomerName(stage.getBtCustomerName());
                    entity.setContractStatusCode(stage.getContractStatusCode());
                    entity.setEndUserName(stage.getEndUserName());
                    entity.setFnetRegion1(stage.getFnetRegion1());
                    entity.setForecastGroupCode(stage.getForecastGroupCode());
                    entity.setModelGroupCode(stage.getModelGroupCode());
                    entity.setNetUsd(ConversionUtils.convertToBigDecimal(stage.getNetUsd()));
                    entity.setOrderedQuantity(ConversionUtils.convertToInt(stage.getOrderedQuantity()));
                    entity.setOrderNumber(stage.getOrderNumber());
                    entity.setProductFamilyCode(stage.getProductFamilyCode());
                    entity.setSalesAgentName(stage.getSalesAgentName());
                    entity.setStAgentName(stage.getStAgentName());
                    entity.setStChannelName(stage.getStChannelName());
                    entity.setStCustomerName(stage.getStCustomerName());
                    entity.setImportControlId(stage.getImportControlId());
                    resultList.add(entity);
                } catch (Exception e) {

                    logger.error("failed to process record", e);
                }
            }
            if (resultList.size() == 0) {
                // nothing to import

                // update import control
                ice.setStatus(ImportControlStatus.NOMATCHINGMODELGROUP);
                importControlRepository.save(ice);
                results.setNumRecordsImported(0);
                return results;
            }

            this.oracleOBIRepository.save(resultList);

            // update import control
            ice.setStatus(ImportControlStatus.IMPORT2SO_START);
            importControlRepository.save(ice);

            // stage 4 create projects and customers
            List<ImportOracleObiEntity> entryList = this.oracleOBIRepository.findByImportControlId(ice.getImportControlId());


            List<ProjectEntity> pList = new ArrayList<ProjectEntity>();

            CustomerEntity wrkCustomer = null;


            for (ImportOracleObiEntity entity : entryList) {

                ProjectEntity pe = new ProjectEntity();


                // create or find customer
                // @TODO which name to use st or bt
                String customerName = entity.getBtCustomerName();

                wrkCustomer = portalService.findCustomerByName(customerName);
                if (wrkCustomer == null) {
                    wrkCustomer = new CustomerEntity();
                    wrkCustomer.setName(customerName);
                    this.customerRepository.save(wrkCustomer);
                }

                //  pe.setName(wrkCustomer.getName());
                //this.projectRepository.save(pe);

                pe.setSalesOrders(new SalesOrderEntity());
                pe.getSalesOrders().setProject(pe);
                //entity.getActivityDate();
                //entity.getActivityMonth();
                //entity.getActivityYear();
                //entity.getBtCustomerName();
                //entity.getContractStatusCode();
                //entity.getEndUserName();

                pe.getSalesOrders().setRegion(entity.getFnetRegion1());
                //entity.getForecastGroupCode();

                pe.getSalesOrders().setModelGroup(entity.getModelGroupCode());
                pe.getSalesOrders().setAmount(entity.getNetUsd());
                //entity.getOrderedQuantity();
                pe.getSalesOrders().setSalesOrderNumber(entity.getOrderNumber());

                //entity.getProductFamilyCode();
                //entity.getSalesAgentName();
                //entity.getStAgentName();
                //entity.getStChannelName();

                pe.getSalesOrders().setCustomer(wrkCustomer);

                pe.getSalesOrders().setImportControlId(entity.getImportControlId());


                pe.addNotes(new ProjectNotesEntity(pe, "Sales Order imported on: " + new Date()));


                pList.add(pe);

            }
            // create the new projects
            this.projectRepository.save(pList);


            // add in default milestone
            // @TODO fix model

            for (ProjectEntity p : pList) {


                p.setProjectActivity(new HashSet<ProjectActivityEntity>());
                ProjectActivityEntity pae = new ProjectActivityEntity(p, Constants.Activities.BOOK_DATE, null);
                p.getProjectActivity().add(pae);
                this.projectActivityRepository.save(pae);


                pae = new ProjectActivityEntity(p, Constants.Activities.KICKOFF_DATE, null);
                p.getProjectActivity().add(pae);
                this.projectActivityRepository.save(pae);

                pae = new ProjectActivityEntity(p, Constants.Activities.ONSITESTART_DATE, null);
                p.getProjectActivity().add(pae);
                this.projectActivityRepository.save(pae);

                pae = new ProjectActivityEntity(p, Constants.Activities.ONSITEEND_DATE, null);
                p.getProjectActivity().add(pae);
                this.projectActivityRepository.save(pae);

                pae = new ProjectActivityEntity(p, Constants.Activities.PLANNINGMEETING_DATE, null);
                p.getProjectActivity().add(pae);
                this.projectActivityRepository.save(pae);

                pae = new ProjectActivityEntity(p, Constants.Activities.REVREC_DATE, null);
                p.getProjectActivity().add(pae);
                this.projectActivityRepository.save(pae);

                pae = new ProjectActivityEntity(p, Constants.Activities.SHIP_DATE, null);
                p.getProjectActivity().add(pae);
                this.projectActivityRepository.save(pae);

            }

            results.setNumRecordsImported(pList.size());
            ice.setStatus(ImportControlStatus.IMPORTSO_COMPLETE);
            importControlRepository.save(ice);

        } catch (CSVConversionFailureException e) {

            logger.error("ERROR converting csv file", e);
            ice.setStatus(ImportControlStatus.ERROR);
            importControlRepository.save(ice);
            throw e;

        }

        // update import control
        ice.setStatus(ImportControlStatus.COMPLETE);
        importControlRepository.save(ice);

        return results;
    }


    public void importMaster(MultipartFile file) throws CSVConversionFailureException {


        if (file == null || file.isEmpty()) {
            // nothing to do
            logger.info("csv file is empty. nothing to do");
            return;
        }

        try {
            this.importMaster(file.getInputStream());

        } catch (IOException e) {
            logger.error("CONVERSION_FAIL_MSG", e);
            throw new CSVConversionFailureException("CONVERSION_FAIL_MSG", e);
        }

    }

    public void importMaster(InputStream inputStream) throws CSVConversionFailureException {
        try {
            // STEP 1 - convert from CSV to objects

            MasterCSVImport masterImporter = new MasterCSVImport();

            // set anonymize on
            masterImporter.setAnonymize(true);

            List<ImportMasterEntity> results = masterImporter.convert(inputStream);

            // STEP 2 - persist
            if (results != null && results.size() > 0) {
                this.importMasterRepository.save(results);
            } else {
                logger.info("nothing to do. no records to import");
                return;
            }

            // STEP 2.5 created customers
            CustomerEntity wrkCustomer = null;
            String customerName = null;
            for (ImportMasterEntity wrkME : results) {

                customerName = wrkME.getName();
                wrkCustomer = portalService.findCustomerByName(customerName);

                if (wrkCustomer == null) {
                    wrkCustomer = new CustomerEntity();
                    wrkCustomer.setName(customerName);
                    this.customerRepository.save(wrkCustomer);
                }

                wrkME.setCustomer(wrkCustomer);

            }

            // STep 2.6 created resources
            /*
            ResourceEntity wrkResource=null;
            String resourceName = null;
            for(ImportMasterEntity wrkME: results){

                customerName = wrkME.getName();
                wrkCustomer = portalService.findCustomerByName(customerName);

                if (wrkCustomer == null) {
                    wrkCustomer = new CustomerEntity();
                    wrkCustomer.setName(customerName);
                    this.customerRepository.save(wrkCustomer);
                }

                wrkME.setCustomer(wrkCustomer);

            }
            */

            // STEP 2.7

            if (this.addTestData) {
                List<ResourceEntity> resourceList = new ArrayList<ResourceEntity>();

                resourceList.add(new ResourceEntity("Bob", "Murray", "Account Team"));
                resourceList.add(new ResourceEntity("Bill", "Murray", "Account Team"));
                resourceList.add(new ResourceEntity("Tammy", "Smith", "Account Team"));
                resourceList.add(new ResourceEntity("Jill", "Jones", "Account Team"));
                resourceList.add(new ResourceEntity("jose", "Dodd", "Account Team"));

                resourceList.add(new ResourceEntity("Tim", "Murray", "Technical"));
                resourceList.add(new ResourceEntity("Gale", "Murray", "Technical"));
                resourceList.add(new ResourceEntity("gill", "Smith", "Technical"));
                resourceList.add(new ResourceEntity("Ted", "Jones", "Technical"));
                resourceList.add(new ResourceEntity("Mark", "Dodd", "Technical"));

                resourceList.add(new ResourceEntity("Tim", "Holden", "Trainer"));
                resourceList.add(new ResourceEntity("helen", "Murray", "Trainer"));
                resourceList.add(new ResourceEntity("Randy", "Smith", "Trainer"));
                resourceList.add(new ResourceEntity("Sandy", "Jones", "Trainer"));
                resourceList.add(new ResourceEntity("Sarah", "Dodd", "Trainer"));

                this.resourceRepository.save(resourceList);

            }

            // STEP 3 - create projects and add saleorders to projects


            SalesOrderEntity newso = null;
            ProjectEntity project = null;
            for (ImportMasterEntity wrkEnt : results) {

                project = this.mapToProjectOrderEntity(wrkEnt);
                this.projectRepository.save(project);
                int count = 0;

                if (this.addTestData) {
                    Iterable<ResourceEntity> resourceList = this.resourceRepository.findAll();
                    ProjectResourceEntity pre = null;
                    String role = "";
                    for (ResourceEntity resource : resourceList) {
                        if (count < 6) {
                            role = Constants.Role.ACCOUNT;
                        } else if (count < 11) {
                            role = Constants.Role.REMOTE;
                        } else {
                            role = Constants.Role.ONSITE;
                        }
                        pre = new ProjectResourceEntity(project, resource, role);

                        this.projectResourceRepository.save(pre);
                        count++;
                    }


                    this.projectNotesRepository.save(new ProjectNotesEntity(project, "The meeting was delayed ue to weather condition"));
                    this.projectNotesRepository.save(new ProjectNotesEntity(project, "we will need more resources for the next phase"));
                    this.projectNotesRepository.save(new ProjectNotesEntity(project, "The meeting went well"));

                }

                // add in milestones
                // every project gets a standard set
                List<ProjectActivityEntity> paeList = new ArrayList<ProjectActivityEntity>();
                paeList.add(new ProjectActivityEntity(project, Constants.Activities.BOOK_DATE, wrkEnt.getBookDate()));
                paeList.add(new ProjectActivityEntity(project, Constants.Activities.KICKOFF_DATE, wrkEnt.getKickoffMeetingDate()));
                paeList.add(new ProjectActivityEntity(project, Constants.Activities.ONSITESTART_DATE, wrkEnt.getOnSiteStartDate()));
                paeList.add(new ProjectActivityEntity(project, Constants.Activities.ONSITEEND_DATE, wrkEnt.getOnSiteEndDate()));
                paeList.add(new ProjectActivityEntity(project, Constants.Activities.PLANNINGMEETING_DATE, wrkEnt.getPlanningMeetingDate()));
                paeList.add(new ProjectActivityEntity(project, Constants.Activities.REVREC_DATE, wrkEnt.getReleaseForRevenueRecDate()));
                paeList.add(new ProjectActivityEntity(project, Constants.Activities.SHIP_DATE, wrkEnt.getShipDate()));

                this.projectActivityRepository.save(paeList);

                newso = this.mapToSalesOrder(wrkEnt);
                newso.setProject(project);
                this.salesOrderRepository.save(newso);


            }

            // STEP 4 - created and assign customer

/*


            if(orders!=null && orders.iterator().hasNext()){


                this.salesOrderRepository.save(orders);

            }else{
                // this is a programing error if we get to this point
                logger.info("Nothing to do no salesorder created");
                return;
            }
*/

        } catch (CSVConversionFailureException e) {

            logger.error("ERROR importing master csv file", e);

            throw e;

        }


    }


    private SalesOrderEntity mapToSalesOrder(ImportMasterEntity importMasterEntity) {
        SalesOrderEntity soe = new SalesOrderEntity();


        soe.setSalesOrderNumber(importMasterEntity.getSalesOrderNumber());
        soe.setAmount(importMasterEntity.getAmount());
        soe.setRegion(importMasterEntity.getRegion());
        soe.setModelGroup(importMasterEntity.getModelGroup());
        //soe.setStSalesAgentName("");
        //soe.setActivityYear();
        //soe.setActivityMonthNumber();
        //soe.setActivityDate();
        soe.setCustomer(importMasterEntity.getCustomer());
        //soe.setStChannelName();
        //soe.setBtCustomerName();
        //soe.setProductFamilyCode(String productFamilyCode));
        //soe.setForecastGroupCode(String forecastGroupCode));
        //soe.setOrderedQuantity(Double orderedQuantity));
        //soe.setContractStatusCode();
        //soe.setEndUserName(String endUserName));


        return soe;
    }

    private ProjectEntity mapToProjectOrderEntity(ImportMasterEntity importMasterEntity) {
        ProjectEntity pe = new ProjectEntity();

        //pe.setsetProjectResources(Set<ProjectResourceEntity> projectResources) {
        //pe.setProjectId(long projectId) {

        // create name cusomername+sonumber


        pe.setName(importMasterEntity.getName());

        //pe.setBookDate(importMasterEntity.getBookDate());
        //pe.setShipDate(importMasterEntity.getShipDate());
        //pe.setPlanningMeetingDate(importMasterEntity.getPlanningMeetingDate());
        //pe.setKickoffMeetingDate(importMasterEntity.getKickoffMeetingDate());
        //pe.setOnSiteStartDate(importMasterEntity.getOnSiteStartDate());
        //pe.setOnSiteEndDate(importMasterEntity.getOnSiteEndDate());
        //pe.setReleaseForRevenueRecDate(importMasterEntity.getReleaseForRevenueRecDate());

        //pe.setBookedToKickOff(importMasterEntity.getBookedToKickOff());
        //pe.setDaysToClose(importMasterEntity.getDaysToClose());

        pe.setService(importMasterEntity.getService());

        // @TODO to be phased out
        // need to map to new model
        pe.setAccountTeam(importMasterEntity.getAccountTeam());
        pe.setRemote(importMasterEntity.getRemote());
        pe.setOnsite(importMasterEntity.getOnsite());

//        pe.setNotes(Set<ProjectNotesEntity> notes) {
        pe.setWaitTime(importMasterEntity.getWaitTime());
        //      pe.setSalesOrders(SalesOrderEntity salesOrders) {
        //pe.setContact(importMasterEntity.getC
        pe.setLocation(importMasterEntity.getLocation());
        pe.setCredits(importMasterEntity.getCredits());
        //pe.setCreditStatus(importMasterEntity.c
        pe.setClassRegSent(importMasterEntity.getClassRegSent());
        pe.setReportedRevRec(importMasterEntity.getReportedRevRec());
        pe.setStatus(importMasterEntity.getStatus());

        return pe;
    }
}
