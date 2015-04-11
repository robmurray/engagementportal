package com.ys.eportal.model;

/**
 * Created by rob on 4/5/15.
 */
public class ProjectSearch {
    private int saleOrderNumber;
    private String customerName;

    public ProjectSearch() {
    }

    public ProjectSearch(String customerName) {
        this.customerName = customerName;
    }

    public ProjectSearch(int saleOrderNumber) {
        this.saleOrderNumber = saleOrderNumber;
    }

    public ProjectSearch(int saleOrderNumber, String customerName) {
        this.saleOrderNumber = saleOrderNumber;
        this.customerName = customerName;
    }

    public int getSaleOrderNumber() {
        return saleOrderNumber;
    }

    public void setSaleOrderNumber(int saleOrderNumber) {
        this.saleOrderNumber = saleOrderNumber;
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
        if (!(o instanceof ProjectSearch)) return false;

        ProjectSearch that = (ProjectSearch) o;

        if (saleOrderNumber != that.saleOrderNumber) return false;
        if (customerName != null ? !customerName.equals(that.customerName) : that.customerName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = saleOrderNumber;
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        return result;
    }
}
