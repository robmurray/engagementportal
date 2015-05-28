package com.ys.em.service.converter;

import com.ys.em.infra.domain.Constants;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rob on 4/14/15.
 */
public class ConversionUtils {

    public static int convertToInt(String stringToConvert) throws NumberFormatException {
        int returnValue = 0;
        if (stringToConvert == null || stringToConvert.trim().equals("")) {
            return returnValue;
        }
        returnValue = Integer.parseInt(StringUtils.stripToEmpty(stringToConvert));
        return returnValue;
    }

    public static Object[] convertCredits(String stringToConvert) throws NumberFormatException {

        Object[] retValues=new Object[2];
        retValues[0]=0;
        retValues[1]= Constants.Credits.NOTSPECIFIED;

        if (stringToConvert == null || stringToConvert.trim().equals("")) {
            return retValues;
        }

        if(StringUtils.isNumeric(stringToConvert)){
            retValues[0]= Integer.parseInt(StringUtils.stripToEmpty(stringToConvert));
            retValues[1]= Constants.Credits.STANDARD;
        }else if(StringUtils.isAlphanumeric(stringToConvert)){
            retValues[0]= 0;
            retValues[1]= stringToConvert;
        }

        return retValues;
    }

    // @TODO 0ms ? how to map
    public static int convertDaysToInt(String stringToConvert) throws NumberFormatException {
        int returnValue = 0;

        if (stringToConvert == null || stringToConvert.trim().equals("")) {
            return returnValue;
        }
        if(stringToConvert.contains("d")){
            stringToConvert=stringToConvert.replace("d","");
        }
        if(stringToConvert.contains("/")){
            return returnValue;
        }
        if(stringToConvert.contains("ms")){
            return returnValue;
        }

        returnValue = Integer.parseInt(StringUtils.stripToEmpty(stringToConvert));
        return returnValue;
    }

    public static BigDecimal convertToBigDecimal(String val) throws NumberFormatException {
        BigDecimal returnValue = new BigDecimal(0.00);
        if (val == null || val.trim().equals("")) {
            return returnValue;
        }

        val = val.replace(",","");
        val = val.replace("$","");

        returnValue = new BigDecimal(val.trim());
        return returnValue;
    }


    public static Date convertToDate(String origdate,String dateFormat) throws ParseException {

        if(dateFormat == null||StringUtils.isEmpty(dateFormat)){
            dateFormat = Constants.DEFAULT_DATE_FORMAT;
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
