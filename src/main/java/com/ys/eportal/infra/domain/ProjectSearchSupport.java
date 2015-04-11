package com.ys.eportal.infra.domain;

/**
 * Created by rob on 4/10/15.
 */
public class ProjectSearchSupport {

    private int salesOrderNumber;
    private String customerName;


    public ProjectSearchSupport() {
    }

    public ProjectSearchSupport(int salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    public ProjectSearchSupport(String customerName) {
        this.customerName = customerName;
    }

    public ProjectSearchSupport(int salesOrderNumber, String customerName) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectSearchSupport)) return false;

        ProjectSearchSupport that = (ProjectSearchSupport) o;

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
