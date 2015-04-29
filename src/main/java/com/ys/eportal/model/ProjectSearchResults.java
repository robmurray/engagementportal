package com.ys.eportal.model;

import com.ys.eportal.infra.domain.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rob on 4/25/15.
 */
public class ProjectSearchResults {
    private Map<String, String> statusDisplayClass = new HashMap<String, String>() {{
        put("", "danger");
        put(Constants.Projects.STATUS_BOOKED, "info");
        put(Constants.Projects.STATUS_COMPLETE, "primary");
        put(Constants.Projects.STATUS_INPROCESS, "success");
        put(Constants.Projects.STATUS_NOTDEFINED, "danger");
        put(Constants.Projects.STATUS_POSTSUPPORT, "danger");
        put(Constants.Projects.STATUS_PROPOSED, "default");
        put(Constants.Projects.STATUS_RANDSUPPORT, "warning");
        put(Constants.Projects.STATUS_SCHEDULED, "success");
    }};

    private Map<String, String> healthStatusDisplayClass = new HashMap<String, String>() {{
        put(Constants.Projects.HEALTH_STATUS_GOOD, "success");
        put(Constants.Projects.HEALTH_STATUS_WARNING, "warning");
        put(Constants.Projects.HEALTH_STATUS_RISK, "danger");

    }};


    private long projectId;

    private long salesOrderId;

    private long customerId;

    private String salesOrderNumber;

    private String customerName;

    private String status;

    private String health;

    private boolean readonly;

    public ProjectSearchResults() {
    }

    public ProjectSearchResults(long projectId, String salesOrderNumber, String customerName, String status) {
        this.projectId = projectId;
        this.salesOrderNumber = salesOrderNumber;
        this.customerName = customerName;
        this.status = status;
    }

    public ProjectSearchResults(long projectId, long salesOrderId, String salesOrderNumber, String customerName, String status) {
        this.projectId = projectId;
        this.salesOrderId = salesOrderId;
        this.salesOrderNumber = salesOrderNumber;
        this.customerName = customerName;
        this.status = status;
    }

    public ProjectSearchResults(long projectId, long salesOrderId, long customerId, String salesOrderNumber, String customerName, String status) {
        this.projectId = projectId;
        this.salesOrderId = salesOrderId;
        this.customerId = customerId;
        this.salesOrderNumber = salesOrderNumber;
        this.customerName = customerName;
        this.status = status;
    }

    public ProjectSearchResults(long projectId, long salesOrderId, long customerId, String salesOrderNumber, String customerName, String status, String health) {
        this.projectId = projectId;
        this.salesOrderId = salesOrderId;
        this.customerId = customerId;
        this.salesOrderNumber = salesOrderNumber;
        this.customerName = customerName;
        this.status = status;
        this.health = health;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(long salesOrderId) {
        this.salesOrderId = salesOrderId;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getStatusDisplayClass() {
        if (status == null) {
            status = "";
        }
        return this.statusDisplayClass.get(status);
    }

    public String getHealthStatusDisplayClass() {
        if (this.health == null) {
            health = Constants.Projects.HEALTH_STATUS_GOOD;
        }
        return this.healthStatusDisplayClass.get(health);
    }

}
