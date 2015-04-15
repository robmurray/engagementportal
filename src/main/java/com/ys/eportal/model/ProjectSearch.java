package com.ys.eportal.model;

import java.util.List;

/**
 * Created by rob on 4/5/15.
 */
public class ProjectSearch {
    private int salesOrderNumber;
    private String customerName;
    private List<String> statusValues = ModelConstants.statusValues;
    private String status;

    public ProjectSearch() {
    }

    public ProjectSearch(String customerName) {
        this.customerName = customerName;
    }

    public ProjectSearch(int salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    public ProjectSearch(int salesOrderNumber, String customerName) {
        this.salesOrderNumber = salesOrderNumber;
        this.customerName = customerName;
    }

    public int getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(int salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectSearch)) return false;

        ProjectSearch that = (ProjectSearch) o;

        if (salesOrderNumber != that.salesOrderNumber) return false;
        if (customerName != null ? !customerName.equals(that.customerName) : that.customerName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = salesOrderNumber;
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        return result;
    }
}
