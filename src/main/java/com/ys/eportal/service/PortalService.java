package com.ys.eportal.service;

import com.ys.eportal.infra.domain.*;
import com.ys.eportal.infra.repository.*;
import com.ys.eportal.model.ModelConstants;
import com.ys.eportal.model.ProjectStats;
import com.ys.eportal.model.UploadSalesOrder;
import com.ys.eportal.service.converter.CSV2SalesOrderConverter;
import com.ys.eportal.service.converter.ConversionResults;
import com.ys.eportal.service.converter.ConversionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rob on 4/4/15.
 */

@Service
@Transactional
public class PortalService {

    private static Logger logger = LoggerFactory.getLogger(PortalService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private ImportOracleOBIStageRepository oracleOBIStageRepository;

    @Autowired
    private ImportOracleOBIRepository oracleOBIRepository;

    @Autowired
    private ImportControlRepository importControlRepository;

    public ProjectStats retrieveProjectStatus() {

        List<Object[]> list = this.salesOrderRepository.retrieveOrderStats();

        ProjectStats proj = new ProjectStats();
        // @TODO improve the ugliness
        if (list != null) {

            for (Object sos[] : list) {
                try {

                    // need to deal with the nordefinedstatus
                    if (sos[0] == null || sos[0].equals(ModelConstants.STATUS_NOTDEFINED)) {
                        proj.setNumNotdefinedStatus(((BigInteger) sos[1]).intValue());
                    } else if (sos[0].equals(ModelConstants.STATUS_BOOKED)) {
                        proj.setNumBookedStatus(((BigInteger) sos[1]).intValue());
                    } else if (sos[0].equals(ModelConstants.STATUS_COMPLETE)) {
                        proj.setNumCompleteStatus(((BigInteger) sos[1]).intValue());
                    } else if (sos[0].equals(ModelConstants.STATUS_INPROCESS)) {
                        proj.setNumInprocessStatus(((BigInteger) sos[1]).intValue());
                    } else if (sos[0].equals(ModelConstants.STATUS_POSTSUPPORT)) {
                        proj.setNumPostSupportStatus(((BigInteger) sos[1]).intValue());
                    } else if (sos[0].equals(ModelConstants.STATUS_PROPOSED)) {
                        proj.setNumProposedStatus(((BigInteger) sos[1]).intValue());
                    } else if (sos[0].equals(ModelConstants.STATUS_RANDSUPPORT)) {
                        proj.setNumRandSupportStatus(((BigInteger) sos[1]).intValue());
                    } else if (sos[0].equals(ModelConstants.STATUS_SCHEDULED)) {
                        proj.setNumScheduledStatus(((BigInteger) sos[1]).intValue());
                    }
                } catch (ClassCastException e) {
                    // ignore. rewrite
                }
            }

        }

        return proj;
    }

    public Iterable<CustomerEntity> find(CustomerSearchSupport search) {
        List<CustomerEntity> results = null;

        if (search.getCustomerId() > 0) {
            CustomerEntity c = this.findCustomerByID(search.getCustomerId());
            if(c!=null) {
                results = new ArrayList<CustomerEntity>();
                results.add(c);
            }
        } else if (search.getName() != null && !search.getName().trim().equals("")) {
            results = this.searchByCustomerName(search.getName());
        } else {
            results = this.findAllCustomers();
        }

        return results;
    }

    public CustomerEntity findCustomerByID(int customerId) {

        return this.customerRepository.findOne(customerId);
    }


    public CustomerEntity findCustomerByName(String name) {
        CustomerEntity customer = null;
        List<CustomerEntity> list = this.customerRepository.findByName(name);
        if (list != null && list.size() > 0) {
            customer = list.get(0);
        }
        return customer;
    }

    public List<CustomerEntity> searchByCustomerName(String name) {

        return this.customerRepository.findByNameLike(this.processWildCards(name));

    }


    public List<CustomerEntity> findAllCustomers() {
        return (List) this.customerRepository.findAll();
    }


    public CustomerEntity saveCustomer(CustomerEntity customer) {
        return this.customerRepository.save(customer);
    }


    public void saveProject(SalesOrderEntity salesOrderEntity) {
        this.salesOrderRepository.save(salesOrderEntity);
    }

    public SalesOrderEntity findSalesOrderEntityById(String salesOrderId) {
        List<SalesOrderEntity> list = this.salesOrderRepository.findBySalesOrderId(salesOrderId);
        SalesOrderEntity soe = null;
        if (list != null && list.size() > 0) {
            soe = list.get(0);
        }
        return soe;
    }

    public List<SalesOrderEntity> findByImportControlId(long importControlId) {
        List<SalesOrderEntity> list = this.salesOrderRepository.findByimportControlId(importControlId);

        return list;
    }

    public Set<SalesOrderEntity> findBySalesOrderCustomerIdlId(int customerId) {
        return this.salesOrderRepository.findByCustomerCustomerId(customerId);

    }

    public List<SalesOrderEntity> findByStatus(String status) {
        List<SalesOrderEntity> list = this.salesOrderRepository.findByStatus(status);

        return list;
    }

    public List<SalesOrderEntity> findAllSalesOrders() {
        return (List) this.salesOrderRepository.findAll();
    }

    public Iterable<SalesOrderEntity> find(ProjectSearchSupport search) {
        List<SalesOrderEntity> results = null;

        if (search.getSalesOrderNumber() != null && !search.getSalesOrderNumber().trim().equals("")) {
            SalesOrderEntity soe = this.findSalesOrderEntityById(search.getSalesOrderNumber());
            if (soe != null) {
                results = new ArrayList<SalesOrderEntity>();
                results.add(soe);
            }
        } else if (search.getCustomerName() != null && !search.getCustomerName().trim().equals("")) {
            results = this.salesOrderRepository.findByCustomerNameLike(this.processWildCards(search.getCustomerName()));
            //results = this.findAllSalesOrders();
        } else if (search.getImportControlId() > 0) {
            results = this.findByImportControlId(search.getImportControlId());

        } else if (search.getStatus() != null && !search.getStatus().trim().equals("")) {
            results = this.findByStatus(search.getStatus());
        } else {
            results = this.findAllSalesOrders();
        }

        return results;
    }

    public ConversionResults<String, ImportOracleObiStage> importOracleOBICSVSalesOrder(MultipartFile file, UploadSalesOrder uploadSalesOrder) throws CSVConversionFailureException {
        ImportControlEntity ice = new ImportControlEntity();

        if (file == null || file.isEmpty()) {
            // nothing to do
            logger.info("csv file is empty. nothing to do");

            ice.setFileName("n/a");
            ice.setStatus(ImportControlStatus.NOTHING2DO);
            importControlRepository.save(ice);

            return new ConversionResults();
        }
        ConversionResults<String, ImportOracleObiStage> results = null;
        CSV2SalesOrderConverter salesOrderConverter = new CSV2SalesOrderConverter();


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
                    ConversionUtils.convertToDate(stage.getActivityDate(), ConversionUtils.DEFAULT_DATE_FORMAT);

                    entity.setActivityDate(ConversionUtils.convertToDate(stage.getActivityDate(), ConversionUtils.DEFAULT_DATE_FORMAT));
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

                wrkCustomer = this.findCustomerByName(customerName);
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
                wrkProject.setSalesOrderId(entity.getOrderNumber());

                //entity.getProductFamilyCode();
                //entity.getSalesAgentName();
                //entity.getStAgentName();
                //entity.getStChannelName();

                wrkProject.setCustomer(new CustomerEntity(wrkCustomer.getCustomerId())); //entity.getStCustomerName();

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

    protected String processWildCards(String value) {

        if (value == null)
            value = "";
        value = value.replaceAll("%", "");
        value = value.replaceAll("\\*", "");
        value = value + "%";
        return value;
    }
}