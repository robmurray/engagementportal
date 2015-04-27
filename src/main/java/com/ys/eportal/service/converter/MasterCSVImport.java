package com.ys.eportal.service.converter;


import com.ys.eportal.infra.domain.ImportMasterEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rob on 4/12/15.
 */
public class MasterCSVImport {
    private static String CONVERSION_FAIL_MSG = "CSV Conversion failure: Failed to create one of input stream, parser or records";
    private static String CONVERSION_RECORD_FAIL_MSG = "CSV Row Conversion failure: Failed to processs csv record:";

    private static Logger logger = LoggerFactory.getLogger(MasterCSVImport.class);
    private static String DEFAULT_ENCODING = "utf-16le";

    boolean skipHeaderRow = true;
    boolean hasHeaderRow = true;
    boolean abortOnRowFailure = true;
    private int expectedTokens;
    private String encoding = DEFAULT_ENCODING;

    private boolean anonymize = true;

    private long importControlId;

    public MasterCSVImport() {
    }

    public boolean isAnonymize() {
        return anonymize;
    }

    public void setAnonymize(boolean anonymize) {
        this.anonymize = anonymize;
    }

    public long getImportControlId() {
        return importControlId;
    }

    public void setImportControlId(long importControlId) {
        this.importControlId = importControlId;
    }

    public boolean isSkipHeaderRow() {
        return skipHeaderRow;
    }

    public void setSkipHeaderRow(boolean skipHeaderRow) {
        this.skipHeaderRow = skipHeaderRow;
    }

    public boolean isHasHeaderRow() {
        return hasHeaderRow;
    }

    public void setHasHeaderRow(boolean hasHeaderRow) {
        this.hasHeaderRow = hasHeaderRow;
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

    public List<ImportMasterEntity> convert(MultipartFile uploadFile) throws CSVConversionFailureException {

        if (uploadFile == null) {
            throw new CSVConversionFailureException("CSV File is null");
        }

        if (uploadFile.isEmpty()) {
            throw new CSVConversionFailureException("CSV File is empty");
        }

        logger.info("encoding=" + this.encoding);
        logger.info("expected tokens=" + expectedTokens);

        List<ImportMasterEntity> results = new ArrayList<ImportMasterEntity>();

        //results.setConvertedRecords(new ArrayList<ImportOracleObiStage>());

        Reader reader = null;
        CSVParser parser = null;
        Iterable<CSVRecord> records = null;

        try {
            reader = new InputStreamReader(new BOMInputStream(uploadFile.getInputStream()), encoding);
            CSVFormat format = CSVFormat.EXCEL;
            format.withAllowMissingColumnNames(false);
            format.withDelimiter(',');
            format.withIgnoreSurroundingSpaces(true);
            format.withIgnoreEmptyLines(true);
            parser = new CSVParser(reader, format);
            records = parser.getRecords();

        } catch (IOException e) {
            logger.error(CONVERSION_FAIL_MSG, e);
            throw new CSVConversionFailureException(CONVERSION_FAIL_MSG, e);
        }


        int recordCount = 0;
        int recordsProcessed = 0;
        int recordsInError = 0;
        if (records != null) {

            ImportMasterEntity convertedObject = null;
            for (CSVRecord record : records) {

                if (this.skipHeaderRow && this.hasHeaderRow) {
                    if (record.getRecordNumber() < 2) {
                        continue;
                    }
                }
                recordCount++;

                try {
                    convertedObject = processLine(record);
                    results.add(convertedObject);
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

        logger.info("#records: " + recordCount);
        logger.info("#records processed: " + recordsProcessed);
        logger.info("#records in error: " + recordsInError);
        return results;
    }


    protected ImportMasterEntity processLine(CSVRecord record) throws CSVRecordConversionFailureException {

        ImportMasterEntity importMaster = new ImportMasterEntity();


        int index = 0;
        long recordNumber =record.getRecordNumber();
        try {
            String classRegSent = StringUtils.stripToNull(record.get(index++));


            String reportedRevRec = StringUtils.stripToNull(record.get(index++));

            Object[] creditsParts = ConversionUtils.convertCredits(record.get(index++));

            int credits = (int) creditsParts[0];
            String creditStatus = (String) creditsParts[1];

            String name = "";
            if (this.anonymize) {
                String wrkName = StringUtils.stripToNull(record.get(index++));
                if (wrkName != null) {
                    name = wrkName.substring(0, 1);
                }
                name = name + "Customer" + recordNumber;

            } else {
                name = StringUtils.stripToNull(record.get(index++));
            }

            String salesOrderNumber = "";
            if (this.anonymize) {
                salesOrderNumber = recordNumber + "000" + recordNumber;
                index++;
            } else {
                salesOrderNumber = StringUtils.stripToNull(record.get(index++));
            }

            String status = StringUtils.stripToNull(record.get(index++));

            Date bookDate = null;
            try {
                bookDate = ConversionUtils.convertToDate(StringUtils.stripToNull(record.get(index++)), ConversionUtils.MASTER_DATE_FORMAT);
            } catch (ParseException e) {

                handleFieldError(e, "bookDate", record);
            }


            Date shipDate = null;
            try {
                shipDate = ConversionUtils.convertToDate(StringUtils.stripToNull(record.get(index++)), ConversionUtils.MASTER_DATE_FORMAT);
            } catch (ParseException e) {
                handleFieldError(e, "shipDate", record);
            }

            Date planningMeetingDate = null;
            try {
                planningMeetingDate = ConversionUtils.convertToDate(StringUtils.stripToNull(record.get(index++)), ConversionUtils.MASTER_DATE_FORMAT);
            } catch (ParseException e) {
                handleFieldError(e, "planningMeetingDate", record);
                ;
            }

            Date kickoffMeetingDate = null;
            try {
                kickoffMeetingDate = ConversionUtils.convertToDate(StringUtils.stripToNull(record.get(index++)), ConversionUtils.MASTER_DATE_FORMAT);
            } catch (ParseException e) {
                handleFieldError(e, "kickOffMeetingDate", record);
            }

            Date onSiteStartDate = null;
            try {
                onSiteStartDate = ConversionUtils.convertToDate(StringUtils.stripToNull(record.get(index++)), ConversionUtils.MASTER_DATE_FORMAT);
            } catch (ParseException e) {
                handleFieldError(e, "onSiteStartDate", record);
            }

            Date onSiteEndDate = null;
            try {
                onSiteEndDate = ConversionUtils.convertToDate(StringUtils.stripToNull(record.get(index++)), ConversionUtils.MASTER_DATE_FORMAT);
            } catch (ParseException e) {
                handleFieldError(e, "onSiteDate", record);
            }

            Date releaseForRevenueRecDate = null;
            try {
                releaseForRevenueRecDate = ConversionUtils.convertToDate(StringUtils.stripToNull(record.get(index++)), ConversionUtils.MASTER_DATE_FORMAT);
            } catch (ParseException e) {
                handleFieldError(e, "releaseForRevenueRecDate", record);
            }

            String waitTime = StringUtils.stripToNull(record.get(index++));


            Integer bookedToKickOff = ConversionUtils.convertDaysToInt(record.get(index++));

            Integer daysToClose = ConversionUtils.convertDaysToInt(record.get(index++));

            BigDecimal amount=null;
            if (this.anonymize) {
                amount = new BigDecimal(recordNumber+1000);
                index++;
            }else{
               amount = ConversionUtils.convertToBigDecimal(record.get(index++));
            }
            String notes = "";

            if (!this.anonymize) {
                notes = StringUtils.stripToNull(record.get(index++));
            }else {
                index++;
            }
            String location = StringUtils.stripToNull(record.get(index++));
            String region = StringUtils.stripToNull(record.get(index++));
            String modelGroup = StringUtils.stripToNull(record.get(index++));

            String service ="service"+recordNumber;
            if (!this.anonymize) {
                service = StringUtils.stripToNull(record.get(index++));
            }else {
                index++;
            }

            String accountTeam="accountteam"+recordNumber;
            if (!this.anonymize) {
               accountTeam = StringUtils.stripToNull(record.get(index++));
            }else {
                index++;
            }

            String remote ="remote"+recordNumber;
            if (!this.anonymize) {
               remote = StringUtils.stripToNull(record.get(index++));
            }else {
                index++;
            }
            String onsite ="onsite"+recordNumber;
            if (!this.anonymize) {
               onsite = StringUtils.stripToNull(record.get(index++));
            }else {
                index++;
            }
            String equipmentList ="equipmentList"+recordNumber;
            if (!this.anonymize) {
                equipmentList = StringUtils.stripToNull(record.get(index++));
            }else {
                index++;
            }

            String shawdow = "shawdow"+recordNumber;
            if (!this.anonymize) {
               shawdow = StringUtils.stripToNull(record.get(index++));
            }else {
                index++;
            }

            // ok to store new object
            /*
            String projectName ="";
            if(c)
            if(salesOrderNumber!=null){

            }

            importMaster.setName();
            */
            importMaster.setSalesOrderNumber(salesOrderNumber);
            importMaster.setAccountTeam(accountTeam);
            importMaster.setAmount(amount);
            importMaster.setBookDate(bookDate);
            importMaster.setBookedToKickOff(bookedToKickOff);
            importMaster.setClassRegSent(classRegSent);
            importMaster.setCredits(credits);
            importMaster.setDaysToClose(daysToClose);
            importMaster.setEquipmentList(equipmentList);
            importMaster.setKickoffMeetingDate(kickoffMeetingDate);
            importMaster.setLocation(location);
            importMaster.setModelGroup(modelGroup);
            importMaster.setName(name);
            importMaster.setNotes(notes);
            importMaster.setOnsite(onsite);
            importMaster.setOnSiteEndDate(onSiteEndDate);
            importMaster.setOnSiteStartDate(onSiteStartDate);
            importMaster.setPlanningMeetingDate(planningMeetingDate);
            importMaster.setRegion(region);
            importMaster.setReleaseForRevenueRecDate(releaseForRevenueRecDate);
            importMaster.setRemote(remote);
            importMaster.setReportedRevRec(reportedRevRec);
            importMaster.setService(service);
            importMaster.setShawdow(shawdow);
            importMaster.setShipDate(shipDate);
            importMaster.setStatus(status);
            importMaster.setWaitTime(waitTime);

        } catch (CSVFieldImportException e) {

            logger.error("Failed to import master CSV. aborting", e);
            logger.error(e.getFieldName());
            logger.error(e.getRecord());
            throw new CSVRecordConversionFailureException(e);
        }

        return importMaster;
    }


    private void handleFieldError(Throwable e, String fieldname, CSVRecord record) throws CSVFieldImportException {

        CSVFieldImportException fieldException = new CSVFieldImportException(e);
        fieldException.setFieldName(fieldname);
        String recordAsString = "";
        if (record != null) {
            recordAsString = record.toString();
        }
        fieldException.setRecord(recordAsString);
        logger.debug(fieldException.getFieldName() + " " + fieldException.getRecord());


        //throw fieldException;
    }
}
