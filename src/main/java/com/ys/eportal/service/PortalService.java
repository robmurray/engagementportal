package com.ys.eportal.service;

import com.ys.eportal.infra.domain.*;
import com.ys.eportal.infra.repository.CustomerRepository;
import com.ys.eportal.infra.repository.ImportOracleOBIRepository;
import com.ys.eportal.infra.repository.ImportOracleOBIStageRepository;
import com.ys.eportal.infra.repository.SalesOrderRepository;
import com.ys.eportal.service.converter.CSV2SalesOrderConverter;
import com.ys.eportal.service.converter.ConversionResults;
import com.ys.eportal.service.converter.ConversionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by rob on 4/4/15.
 */

@Service
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

    public Iterable<CustomerEntity> find(CustomerSearchSupport search) {
        List<CustomerEntity> results = null;

        if (search.getCustomerId() > 0) {
            CustomerEntity c = this.findCustomerByID(search.getCustomerId());
            results = new ArrayList<CustomerEntity>();
            results.add(c);
        } else if (search.getName() != null && !search.getName().trim().equals("")) {
            results = this.findCustomerByName(search.getName());
        } else {
            results = this.findAllCustomers();
        }

        return results;
    }

    public CustomerEntity findCustomerByID(int customerId) {

        return this.customerRepository.findOne(customerId);
    }


    public List<CustomerEntity> findCustomerByName(String name) {
        return this.customerRepository.findByName(name);
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

    public SalesOrderEntity findSalesOrderEntityById(int salesOrderId) {
        List<SalesOrderEntity> list = this.salesOrderRepository.findBySalesOrderId(salesOrderId);
        SalesOrderEntity soe = null;
        if (list != null && list.size() > 0) {
            soe = list.get(0);
        }
        return soe;
    }

    public List<SalesOrderEntity> findAllSalesOrders() {
        return (List) this.salesOrderRepository.findAll();
    }

    public Iterable<SalesOrderEntity> find(ProjectSearchSupport search) {
        List<SalesOrderEntity> results = null;

        if (search.getSalesOrderNumber() > 0) {
            SalesOrderEntity soe = this.findSalesOrderEntityById(search.getSalesOrderNumber());
            results = new ArrayList<SalesOrderEntity>();
            results.add(soe);
        } else if (search.getCustomerName() != null && !search.getCustomerName().trim().equals("")) {
            //results = this.findCustomerByName(search.getName());
            results = this.findAllSalesOrders();
        } else {
            results = this.findAllSalesOrders();
        }

        return results;
    }

    public ConversionResults<String, ImportOracleObiStage> importOracleOBICSVSalesOrder(MultipartFile file) throws CSVConversionFailureException {
        if (file == null || file.isEmpty()) {
            // nothing to do
            logger.info("csv file is empty. nothing to do");
            return new ConversionResults();
        }
        ConversionResults<String, ImportOracleObiStage> results = null;
        CSV2SalesOrderConverter salesOrderConverter = new CSV2SalesOrderConverter();

        try {

            // step 1 csv to staging
            // @TODO create tracking table
            long batchId = 1;

            results = salesOrderConverter.convert(file);
            if (results != null && results.getNumRecordsProcessed() > 0) {

                Collection<ImportOracleObiStage> importResults = results.getConvertedRecords().values();
                this.oracleOBIStageRepository.save(importResults);

            }

            // step 2 staging to import
            List<ImportOracleObiStage> wrkList = this.oracleOBIStageRepository.findByImportControlId(batchId);
            List<ImportOracleObiEntity> resultList = new ArrayList<ImportOracleObiEntity>();


            for (ImportOracleObiStage stage : wrkList) {

                try {
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
                    entity.setOrderNumber(ConversionUtils.convertToInt(stage.getOrderNumber()));
                    entity.setProductFamilyCode(stage.getProductFamilyCode());
                    entity.setSalesAgentName(stage.getSalesAgentName());
                    entity.setStAgentName(stage.getStAgentName());
                    entity.setStChannelName(stage.getStChannelName());
                    entity.setStCustomerName(stage.getStCustomerName());
                    resultList.add(entity);
                } catch (Exception e) {

                    logger.error("failed to process record", e);
                }
            }
            this.oracleOBIRepository.save(resultList);

            // stage 4 create projects and customers
            List<ImportOracleObiEntity> entryList = this.oracleOBIRepository.findByImportControlId(batchId);
            

            this.customerRepository



        } catch (CSVConversionFailureException e) {
            logger.error("ERROR converting csv file", e);
            throw e;
        }


        return results;
    }
}