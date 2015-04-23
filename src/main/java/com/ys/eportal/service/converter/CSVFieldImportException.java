package com.ys.eportal.service.converter;

/**
 * Created by rob on 4/22/15.
 */
public class CSVFieldImportException extends Exception{
    private String fieldName;
    private String record;

    public CSVFieldImportException() {
    }

    public CSVFieldImportException(String message) {
        super(message);
    }

    public CSVFieldImportException(String message, Throwable cause) {
        super(message, cause);
    }

    public CSVFieldImportException(Throwable cause) {
        super(cause);
    }

    public CSVFieldImportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}
