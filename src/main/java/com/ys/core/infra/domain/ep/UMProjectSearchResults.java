package com.ys.core.infra.domain.ep;

import javax.persistence.*;
import java.math.BigInteger;
import java.security.Timestamp;
import java.util.Date;

/**
 * Created by rob on 5/25/15.
 */
@Entity
@SqlResultSetMapping(name = "ProjectSearchResult",
        classes = {
                @ConstructorResult(targetClass = com.ys.core.infra.domain.ep.UMProjectSearchResults.class,
                        columns = {
                                @ColumnResult(name = "project_id", type=long.class),
                                @ColumnResult(name = "sales_order_number", type=String.class),
                                @ColumnResult(name = "sales_order_id", type=long.class),
                                @ColumnResult(name = "model_group", type=String.class),
                                @ColumnResult(name = "customer_id", type=long.class),
                                @ColumnResult(name = "name", type=String.class),
                                @ColumnResult(name = "status", type=String.class),
                                @ColumnResult(name = "health", type=String.class),
                                @ColumnResult(name = "bookedDate", type= Date.class)})

        })
public class UMProjectSearchResults {


    @Id
    private long projectId;
    private String salesOrderNumber;
    private long salesOrderId;
    private String modelGroup;
    private long customerId;
    private String customerName;
    private String status;
    private String health;
    private Date bookDate;

    public UMProjectSearchResults() {
    }

    public UMProjectSearchResults(long projectId, String salesOrderNumber, long salesOrderId, String modelGroup, long customerId, String customerName, String status, String health, Date bookDate) {
        this.projectId = projectId;
        this.salesOrderNumber = salesOrderNumber;
        this.salesOrderId = salesOrderId;
        this.modelGroup = modelGroup;
        this.customerId = customerId;
        this.customerName = customerName;
        this.status = status;
        this.health = health;
        this.bookDate = bookDate;
    }

    public long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(String salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    public String getModelGroup() {
        return modelGroup;
    }

    public void setModelGroup(String modelGroup) {
        this.modelGroup = modelGroup;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UMProjectSearchResults)) return false;

        UMProjectSearchResults that = (UMProjectSearchResults) o;

        if (projectId != that.projectId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (projectId ^ (projectId >>> 32));
    }
}
