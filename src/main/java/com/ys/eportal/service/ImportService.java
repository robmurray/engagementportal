package com.ys.eportal.service;

import com.ys.eportal.infra.domain.*;
import com.ys.eportal.infra.repository.*;
import com.ys.eportal.model.ProjectNote;
import com.ys.eportal.model.Resource;
import com.ys.eportal.model.UploadSalesOrder;
import com.ys.eportal.service.converter.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * Created by rob on 4/22/15.
 */
@Service
public class ImportService extends ServicesBase {

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

    public ConversionResults<ImportOracleObiStage> importOracleOBICSVSalesOrder(MultipartFile file, UploadSalesOrder uploadSalesOrder) throws CSVConversionFailureException {
        ImportControlEntity ice = new ImportControlEntity();

        if (file == null || file.isEmpty()) {
            // nothing to do
            logger.info("csv file is empty. nothing to do");

            ice.setFileName("n/a");
            ice.setStatus(ImportControlStatus.NOTHING2DO);
            importControlRepository.save(ice);

            return new ConversionResults();
        }
        ConversionResults<ImportOracleObiStage> results = null;
        OracleOBISalesOrderCSVImport salesOrderConverter = new OracleOBISalesOrderCSVImport();


        try {
            // step 0 create import Control record

            // update import control
            ice.setFileName(file.getName());
            ice.setStatus(ImportControlStatus.BEGIN);
            ice.setEncoding(salesOrderConverter.getEncoding());

/*
            try {
                ice.setImportFile(file.getBytes());
            } catch (IOException e) {
                // if there was an issue with the file we would never get this far
                logger.error("unable to save csv file to stage", e);
                ice.setStatus(ImportControlStatus.ERROR);
                importControlRepository.save(ice);
                throw new CSVConversionFailureException("unable to save csv file to stage",e);
            }
*/
            importControlRepository.save(ice);


            // set the import control id
            salesOrderConverter.setImportControlId(ice.getImportControlId());
            // step 1 csv to staging

            try {
                results = salesOrderConverter.convert(file);
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

            String modelgroupsToImport = "";
            if (uploadSalesOrder != null) {
                modelgroupsToImport = uploadSalesOrder.getModelgroups();
                if (modelgroupsToImport == null) {
                    modelgroupsToImport = "";
                }
            }

            for (ImportOracleObiStage stage : wrkList) {

                try {


                    if (stage == null || stage.getModelGroupCode() == null || !modelgroupsToImport.contains(stage.getModelGroupCode())) {
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


            List<SalesOrderEntity> soList = new ArrayList<SalesOrderEntity>();

            CustomerEntity wrkCustomer = null;
            SalesOrderEntity wrkProject = null;

            for (ImportOracleObiEntity entity : entryList) {

                // create or find customer
                // @TODO which name to use st or bt
                String customerName = entity.getBtCustomerName();

                wrkCustomer = portalService.findCustomerByName(customerName);
                if (wrkCustomer == null) {
                    wrkCustomer = new CustomerEntity();
                    wrkCustomer.setName(customerName);
                    this.customerRepository.save(wrkCustomer);
                }

                // create the project

                wrkProject = new SalesOrderEntity();


                //entity.getActivityDate();
                //entity.getActivityMonth();
                //entity.getActivityYear();
                //entity.getBtCustomerName();
                //entity.getContractStatusCode();
                //entity.getEndUserName();

                wrkProject.setRegion(entity.getFnetRegion1());
                //entity.getForecastGroupCode();

                wrkProject.setModelGroup(entity.getModelGroupCode());
                wrkProject.setAmount(entity.getNetUsd());
                //entity.getOrderedQuantity();
                wrkProject.setSalesOrderNumber(entity.getOrderNumber());

                //entity.getProductFamilyCode();
                //entity.getSalesAgentName();
                //entity.getStAgentName();
                //entity.getStChannelName();

                //  wrkProject.setCustomer(new CustomerEntity(wrkCustomer.getCustomerId())); //entity.getStCustomerName();

                wrkProject.setImportControlId(entity.getImportControlId());
                soList.add(wrkProject);

            }
            // create the new projects
            this.salesOrderRepository.save(soList);
            results.setNumRecordsImported(soList.size());
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
            // STEP 1 - convert from CSV to objects

            MasterCSVImport masterImporter = new MasterCSVImport();

            // set anonymize on
            masterImporter.setAnonymize(true);

            List<ImportMasterEntity> results = masterImporter.convert(file);

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

            if(this.addTestData){
                List<ResourceEntity> resourceList = new ArrayList<ResourceEntity>();

                resourceList.add(new ResourceEntity("Bob", "Murray", "Account Team"));
                resourceList.add(new ResourceEntity("Bill","Murray","Account Team"));
                resourceList.add(new ResourceEntity("Tammy","Smith","Account Team"));
                resourceList.add(new ResourceEntity("Jill","Jones","Account Team"));
                resourceList.add(new ResourceEntity("jose","Dodd","Account Team"));

                resourceList.add(new ResourceEntity("Tim", "Murray", "Technical"));
                resourceList.add(new ResourceEntity("Gale","Murray","Technical"));
                resourceList.add(new ResourceEntity("gill","Smith","Technical"));
                resourceList.add(new ResourceEntity("Ted","Jones","Technical"));
                resourceList.add(new ResourceEntity("Mark","Dodd","Technical"));

                resourceList.add(new ResourceEntity("Tim", "Holden", "Trainer"));
                resourceList.add(new ResourceEntity("helen","Murray","Trainer"));
                resourceList.add(new ResourceEntity("Randy","Smith","Trainer"));
                resourceList.add(new ResourceEntity("Sandy","Jones","Trainer"));
                resourceList.add(new ResourceEntity("Sarah","Dodd","Trainer"));

                this.resourceRepository.save(resourceList);

            }

            // STEP 3 - create projects and add saleorders to projects


            SalesOrderEntity newso = null;
            ProjectEntity project = null;
            for (ImportMasterEntity wrkEnt : results) {

                project = this.mapToProjectOrderEntity(wrkEnt);
                this.projectRepository.save(project);
                int count=0;

                if(this.addTestData) {
                    Iterable<ResourceEntity> resourceList = this.resourceRepository.findAll();
                    ProjectResourceEntity pre = null;
                    String role="";
                    for(ResourceEntity resource:resourceList) {
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


                    this.projectNotesRepository.save(new ProjectNotesEntity(project,"The meeting was delayed ue to weather condition"));
                    this.projectNotesRepository.save(new ProjectNotesEntity(project,"we will need more resources for the next phase"));
                    this.projectNotesRepository.save(new ProjectNotesEntity(project,"The meeting went well"));

                }

                // add in milestones
                // every project gets a standard set
                List<ProjectActivityEntity> paeList = new ArrayList<ProjectActivityEntity>();
                paeList.add(new ProjectActivityEntity(project,Constants.Activities.BOOK_DATE,project.getBookDate()));
                paeList.add(new ProjectActivityEntity(project,Constants.Activities.KICKOFF_DATE,project.getKickoffMeetingDate()));
                paeList.add(new ProjectActivityEntity(project,Constants.Activities.ONSITESTART_DATE,project.getOnSiteStartDate()));
                paeList.add(new ProjectActivityEntity(project,Constants.Activities.ONSITEEND_DATE,project.getOnSiteEndDate()));
                paeList.add(new ProjectActivityEntity(project,Constants.Activities.PLANNINGMEETING_DATE,project.getPlanningMeetingDate()));
                paeList.add(new ProjectActivityEntity(project,Constants.Activities.REVREC_DATE,project.getReleaseForRevenueRecDate()));
                paeList.add(new ProjectActivityEntity(project,Constants.Activities.SHIP_DATE,project.getShipDate()));

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

        pe.setBookDate(importMasterEntity.getBookDate());
        pe.setShipDate(importMasterEntity.getShipDate());
        pe.setPlanningMeetingDate(importMasterEntity.getPlanningMeetingDate());
        pe.setKickoffMeetingDate(importMasterEntity.getKickoffMeetingDate());
        pe.setOnSiteStartDate(importMasterEntity.getOnSiteStartDate());
        pe.setOnSiteEndDate(importMasterEntity.getOnSiteEndDate());
        pe.setReleaseForRevenueRecDate(importMasterEntity.getReleaseForRevenueRecDate());

        pe.setBookedToKickOff(importMasterEntity.getBookedToKickOff());
        pe.setDaysToClose(importMasterEntity.getDaysToClose());

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
