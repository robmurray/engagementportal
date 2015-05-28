package com.ys.ui.model;

import com.ys.ui.model.AbstractModelBase;

/**
 * Created by rob on 4/27/15.
 */
public class Navigation extends AbstractModelBase {
    protected String returnURL;
    protected String anchor;

    public Navigation() {
    }

    public Navigation(String returnURL) {
        this.returnURL = returnURL;
    }

    public String getAnchor() {
        return anchor;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public String getReturnURL() {
        if (anchor != null) {

        }

        return returnURL;

    }

    public void setReturnURL(String returnURL) {
        this.returnURL = returnURL;
    }


}
