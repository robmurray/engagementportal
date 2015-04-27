package com.ys.eportal.model;

import java.util.Date;

/**
 * Created by rob on 4/9/15.
 */
public abstract class AbstractModelBase {

    protected Date createDate;

    protected Date modifiedDate;


    public Date getCreateDate() {
        return createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }
}
