package com.ys.em.util;

/**
 * Created by rob on 4/14/15.
 */
public class CSVConversionFailureException extends Exception {
    public CSVConversionFailureException() {
    }

    public CSVConversionFailureException(String message) {
        super(message);
    }

    public CSVConversionFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public CSVConversionFailureException(Throwable cause) {
        super(cause);
    }

    public CSVConversionFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
