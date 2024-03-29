package com.ys.em.model;

import com.ys.em.infra.domain.Constants;

import java.util.List;

/**
 * Created by rob on 4/5/15.
 */
public class ProjectSearch {
    private String salesOrderNumber;
    private long salesOrderId;
    private String customerName;
    private long importControlId;
    private String modelGroup;
    private List<String> statusValues = Constants.Projects.statusValues;
    private List<String> modelGroupValues = Constants.modelGroupValues;
    private String status;

    public ProjectSearch() {
    }

    public ProjectSearch(String customerName) {
        this.customerName = customerName;
    }

    public String getModelGroup() {
        return modelGroup;
    }

    public void setModelGroup(String modelGroup) {
        this.modelGroup = modelGroup;
    }

    public List<String> getModelGroupValues() {
        return modelGroupValues;
    }

    public void setModelGroupValues(List<String> modelGroupValues) {
        this.modelGroupValues = modelGroupValues;
    }

    public void setSalesOrderNumber(String salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    public long getImportControlId() {
        return importControlId;
    }

    public void setImportControlId(long importControlId) {
        this.importControlId = importControlId;
    }

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<String> getStatusValues() {
        return statusValues;
    }

    public void setStatusValues(List<String> statusValues) {
        this.statusValues = statusValues;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectSearch)) return false;

        ProjectSearch that = (ProjectSearch) o;

        if (salesOrderNumber != null ? !salesOrderNumber.equals(that.salesOrderNumber) : that.salesOrderNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return salesOrderNumber != null ? salesOrderNumber.hashCode() : 0;
    }
}
