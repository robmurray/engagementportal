package com.ys.em.infra.domain;

/**
 * Created by rob on 4/10/15.
 */
public class ProjectSearchSupport {

    private String salesOrderNumber;
    private String customerName;
    private long importControlId;
    private String modelGroup;
    private String status;

    public ProjectSearchSupport() {
    }

    public String getModelGroup() {
        return modelGroup;
    }

    public void setModelGroup(String modelGroup) {
        this.modelGroup = modelGroup;
    }

    public ProjectSearchSupport(String customerName) {
        this.customerName = customerName;
    }

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public long getImportControlId() {
        return importControlId;
    }

    public void setImportControlId(long importControlId) {
        this.importControlId = importControlId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSalesOrderNumber(String salesOrderNumber) {
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

        if (salesOrderNumber != null ? !salesOrderNumber.equals(that.salesOrderNumber) : that.salesOrderNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return salesOrderNumber != null ? salesOrderNumber.hashCode() : 0;
    }
}
