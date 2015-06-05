package com.ys.em.service.converter;


import com.ys.em.infra.domain.ImportOracleObiStage;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by rob on 4/12/15.
 */
public class OracleOBISalesOrderCSVImport {
    private static String CONVERSION_FAIL_MSG = "CSV Conversion failure: Failed to create one of input stream, parser or records";
    private static String CONVERSION_RECORD_FAIL_MSG = "CSV Row Conversion failure: Failed to processs csv record:";

    private static Logger logger = LoggerFactory.getLogger(OracleOBISalesOrderCSVImport.class);
    private static String DEFAULT_ENCODING = "utf-16le";
//    private static String DEFAULT_ENCODING = "utf-8";

    boolean abortOnRowFailure = false;
    private int expectedTokens;
    private String encoding = DEFAULT_ENCODING;

    private long importControlId;

    public OracleOBISalesOrderCSVImport() {
    }

    public long getImportControlId() {
        return importControlId;
    }

    public void setImportControlId(long importControlId) {
        this.importControlId = importControlId;
    }


    public int getExpectedTokens() {
        return expectedTokens;
    }

    public void setExpectedTokens(int expectedTokens) {
        this.expectedTokens = expectedTokens;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public ConversionResults<ImportOracleObiStage> convert(MultipartFile uploadFile) throws CSVConversionFailureException {
        if (uploadFile == null) {
            throw new CSVConversionFailureException("CSV File is null");
        }

        if (uploadFile.isEmpty()) {
            throw new CSVConversionFailureException("CSV File is empty");
        }

        InputStream is = null;
        try {
            is = uploadFile.getInputStream();
        } catch (IOException e) {
            throw new CSVConversionFailureException("error aquiring input stream", e);
        }
        return this.convert(is);
    }

    public ConversionResults<ImportOracleObiStage> convert(InputStream inputStream) throws CSVConversionFailureException {


        logger.info("encoding=" + this.encoding);
        logger.info("expected tokens=" + expectedTokens);

        ConversionResults<ImportOracleObiStage> results = new ConversionResults<ImportOracleObiStage>();
        results.setConvertedRecords(new ArrayList<ImportOracleObiStage>());


        Reader reader = null;
        CSVParser parser = null;
        Iterable<CSVRecord> records = null;
        Map<String, Integer> headerMap = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new BOMInputStream(inputStream)), encoding), 500);

            CSVFormat format = CSVFormat.TDF
                    .withIgnoreEmptyLines(true)
                    .withHeader()
                    .withIgnoreSurroundingSpaces(true)
                    .withSkipHeaderRecord(false);


            parser = new CSVParser(reader, format);

            headerMap = parser.getHeaderMap();
            records = parser.getRecords();

        } catch (IOException e) {
            logger.error(CONVERSION_FAIL_MSG, e);
            throw new CSVConversionFailureException(CONVERSION_FAIL_MSG, e);
        }

        int recordCount = 0;
        int recordsProcessed = 0;
        int recordsInError = 0;
        if (records != null) {

            ImportOracleObiStage convertedObject = null;
            for (CSVRecord record : records) {
                recordCount++;
                try {
                    convertedObject = processLine(record);
                    results.getConvertedRecords().add(convertedObject);
                    recordsProcessed++;
                } catch (Exception e) {
                    recordsInError++;
                    logger.error(CONVERSION_RECORD_FAIL_MSG, e);
                    if (this.abortOnRowFailure) {
                        throw new CSVConversionFailureException("Failed to process row and abortOnRowFailure is set to true", e);
                    }
                }
            }
        }

        results.setNumRecordsProcessed(recordsProcessed);
        results.setNumRecordsToProcess(recordCount);
        results.setNumRecordsInError(recordsInError);
        logger.info("#records: " + recordCount);
        logger.info("#records processed: " + recordsProcessed);
        logger.info("#records in error: " + recordsInError);


        return results;
    }


    protected ImportOracleObiStage processLine(CSVRecord record) throws CSVRecordConversionFailureException {

        ImportOracleObiStage oracleObi = new ImportOracleObiStage();


        int index = 0;

        try {
            String fnetRegion1 = StringUtils.stripToNull(record.get("﻿FNET Region 1"));
            String salesAgentName = StringUtils.stripToNull(record.get("Sales Agent Name"));
            String stAgentName = StringUtils.stripToNull(record.get("ST Sales Agent Name"));
            String activityYear = StringUtils.stripToNull(record.get("Activity Yr"));
            String activityMonth = StringUtils.stripToNull(record.get("Activity Mo No"));
            String activityDate = StringUtils.stripToNull(record.get("Activity Date"));
            String stCustomerName = StringUtils.stripToNull(record.get("ST Customer Name"));
            String stChannelName = StringUtils.stripToNull(record.get("ST Channel Name"));
            String btCustomerName = StringUtils.stripToNull(record.get("BT Customer Name"));

            String orderNumber = StringUtils.stripToNull(record.get("Order No"));
            if (orderNumber == null) {
                orderNumber = "MISSING" + record.getRecordNumber();
            }

            String productFamilyCode = StringUtils.stripToNull(record.get("Product Family Code"));
            String modelGroupCode = StringUtils.stripToNull(record.get("Model Group Code"));
            String forecastGroupCode = StringUtils.stripToNull(record.get("Forecast Group Code"));
            //String model = StringUtils.stripToNull(record.get("Model"));
            //String itemDesc = StringUtils.stripToNull(record.get("Item Desc"));
            String orderedQuantity = StringUtils.stripToNull(record.get("Ordered Qty"));
            String netUsd = StringUtils.stripToNull(record.get("Net USD"));
            String contractStatusCode = StringUtils.stripToNull(record.get("Contract Status Code"));
            String endUserName = StringUtils.stripToNull(record.get("End User Name"));


            // ok to store new object

            oracleObi.setActivityDate(activityDate);
            oracleObi.setActivityMonth(activityMonth);
            oracleObi.setActivityYear(activityYear);
            oracleObi.setBtCustomerName(btCustomerName);
            oracleObi.setContractStatusCode(contractStatusCode);
            oracleObi.setEndUserName(endUserName);
            oracleObi.setFnetRegion1(fnetRegion1);
            oracleObi.setForecastGroupCode(forecastGroupCode);
            oracleObi.setModelGroupCode(modelGroupCode);
            oracleObi.setNetUsd(netUsd);
            oracleObi.setOrderedQuantity(orderedQuantity);
            oracleObi.setOrderNumber(orderNumber);
            oracleObi.setProductFamilyCode(productFamilyCode);
            oracleObi.setSalesAgentName(salesAgentName);
            oracleObi.setStAgentName(stAgentName);
            oracleObi.setStChannelName(stChannelName);
            oracleObi.setStCustomerName(stCustomerName);
            oracleObi.setImportControlId(this.importControlId);
//        oracleObi.setModel(model);
            //      oracleObi.setItemDesc(itemDesc);

        } catch (IllegalArgumentException e) {
            logger.error("import file format may have changed",e);
        }
        return oracleObi;
    }

}
