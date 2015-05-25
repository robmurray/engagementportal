package com.ys.core.infra.domain.ep;

import java.io.Serializable;
import javax.persistence.*;
/**
 * Created by rob on 4/8/15.
 */
@Entity
@Table(name = "ep_SalesOrderStatus")
public class SalesOrderStatusEntity implements Serializable {

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

        SalesOrderStatusEntity that = (SalesOrderStatusEntity) o;

        if (statusCode != null ? !statusCode.equals(that.statusCode) : that.statusCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return statusCode != null ? statusCode.hashCode() : 0;
    }
}
