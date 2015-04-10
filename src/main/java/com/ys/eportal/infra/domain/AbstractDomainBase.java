package com.ys.eportal.infra.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
/**
 * Created by rob on 4/8/15.
 */

@MappedSuperclass
public class AbstractDomainBase  implements Serializable{

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "createDate", nullable = false, updatable=false)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "modifiedDate", nullable = false)
    private Date modifiedDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @PrePersist
    protected void onCreate() {
        modifiedDate = createDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedDate = new Date();
    }
}
