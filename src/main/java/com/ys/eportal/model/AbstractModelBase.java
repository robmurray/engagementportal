package com.ys.eportal.model;

import java.util.Date;

/**
 * Created by rob on 4/9/15.
 */
public abstract class AbstractModelBase {

    protected boolean readonly;

    protected Date createDate;

    protected Date modifiedDate;

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
