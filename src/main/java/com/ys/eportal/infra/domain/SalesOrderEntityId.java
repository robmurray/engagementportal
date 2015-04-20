package com.ys.eportal.infra.domain;
import java.io.Serializable;

/**
 * Created by rob on 4/10/15.
 */
public class SalesOrderEntityId  implements Serializable {
    private String salesOrderId;
    private int customerId;

    public SalesOrderEntityId() {
    }


    public String getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(String salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalesOrderEntityId)) return false;

        SalesOrderEntityId that = (SalesOrderEntityId) o;

        if (customerId != that.customerId) return false;
        if (salesOrderId != null ? !salesOrderId.equals(that.salesOrderId) : that.salesOrderId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = salesOrderId != null ? salesOrderId.hashCode() : 0;
        result = 31 * result + customerId;
        return result;
    }
}
