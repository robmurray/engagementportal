package com.ys.eportal.infra.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by rob on 4/8/15.
 */
@Entity
@Table(name = "ep_SalesOrderStatus", schema = "", catalog = "engagementportal")
public class SalesOrderStatus implements Serializable {

    @Id
    @Column(name = "statusCode")
    private String statusCode;


    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalesOrderStatus that = (SalesOrderStatus) o;

        if (statusCode != null ? !statusCode.equals(that.statusCode) : that.statusCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return statusCode != null ? statusCode.hashCode() : 0;
    }
}
