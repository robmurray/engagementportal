package com.ys.eportal.service.converter;

/**
 * Created by rob on 4/14/15.
 */
public class CSVRecordConversionFailureException extends Exception {
    public CSVRecordConversionFailureException() {
    }

    public CSVRecordConversionFailureException(String message) {
        super(message);
    }

    public CSVRecordConversionFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public CSVRecordConversionFailureException(Throwable cause) {
        super(cause);
    }

    public CSVRecordConversionFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
