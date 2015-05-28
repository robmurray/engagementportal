package com.ys.ui.model;


import org.springframework.format.annotation.DateTimeFormat;
import com.ys.ui.model.PageModelBase;

import java.util.Date;

/**
 * Created by rob on 4/9/15.
 */
public abstract class AbstractModelBase extends PageModelBase {


    @DateTimeFormat(pattern = "dd/MM/yyyy")
    protected Date createDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    protected Date modifiedDate;

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