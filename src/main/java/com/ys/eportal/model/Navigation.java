package com.ys.eportal.model;

/**
 * Created by rob on 4/27/15.
 */
public class Navigation extends AbstractModelBase{
    protected String returnURL;

    public Navigation(String returnURL) {
        this.returnURL = returnURL;
    }

    public String getReturnURL() {
        return returnURL;
    }

    public void setReturnURL(String returnURL) {
        this.returnURL = returnURL;
    }




}
