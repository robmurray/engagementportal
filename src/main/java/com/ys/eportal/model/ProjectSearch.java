package com.ys.eportal.model;

/**
 * Created by rob on 4/5/15.
 */
public class ProjectSearch {
    private long projectId;
    private long customerId;
    private long customerLastName;

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(long customerLastName) {
        this.customerLastName = customerLastName;
    }
}
