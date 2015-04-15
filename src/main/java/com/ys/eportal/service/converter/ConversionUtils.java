package com.ys.eportal.service.converter;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rob on 4/14/15.
 */
public class ConversionUtils {
    public static final String DEFAULT_DATE_FORMAT = "yyyy-mm-dd";

    public static int convertToInt(String stringToConvert) throws NumberFormatException {
        int returnValue = 0;
        if (stringToConvert == null || stringToConvert.trim().equals("")) {
            return returnValue;
        }
        returnValue = Integer.parseInt(StringUtils.stripToEmpty(stringToConvert));
        return returnValue;
    }

    public static BigDecimal convertToBigDecimal(String val) {
        BigDecimal returnValue = new BigDecimal(0.00);
        if (val == null || val.trim().equals("")) {
            return returnValue;
        }
        returnValue = new BigDecimal(val.trim());
        return returnValue;
    }


    public static Date convertToDate(String origdate,String dateFormat) throws ParseException {

        if(dateFormat == null||StringUtils.isEmpty(dateFormat)){
            dateFormat = DEFAULT_DATE_FORMAT;
        }

        origdate = StringUtils.stripToNull(origdate);
        Date ret = null;
        if (origdate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            sdf.setLenient(false);
            ret = sdf.parse(origdate);

        }
        return ret;
    }
}
