package com.ys.core.service;

/**
 * Created by rob on 4/22/15.
 */
public abstract class ServicesBase {


    protected String processWildCards(String value) {

        if (value == null)
            value = "";
        value = value.replaceAll("%", "");
        value = value.replaceAll("\\*", "");
        value = value + "%";
        return value;
    }

}
