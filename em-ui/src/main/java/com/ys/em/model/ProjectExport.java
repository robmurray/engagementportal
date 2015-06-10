package com.ys.em.model;

import com.ys.em.infra.domain.Constants;
import com.ys.ui.model.AbstractModelBase;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

/**
 * Created by rob on 4/5/15.
 */
public class ProjectExport extends AbstractModelBase {
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
    private long credits;
    private SalesOrder salesOrder;
    private String name;
    private String status;
    private String service;
    private Long bookedToKickOff;
    private Long daysToClose;
    private String waitTime;
    private String location;
    private long salesOrderId;
    private long customerId;
    private String salesOrderNumber;
    private String customerName;
    private String modelGroup;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date bookedDate;
    private String health;

    public ProjectExport() {
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getCredits() {
        return credits;
    }

    public void setCredits(long credits) {
        this.credits = credits;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Long getBookedToKickOff() {
        return bookedToKickOff;
    }

    public void setBookedToKickOff(Long bookedToKickOff) {
        this.bookedToKickOff = bookedToKickOff;
    }

    public Long getDaysToClose() {
        return daysToClose;
    }

    public void setDaysToClose(Long daysToClose) {
        this.daysToClose = daysToClose;
    }

    public String getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(String waitTime) {
        this.waitTime = waitTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getModelGroup() {
        return modelGroup;
    }

    public void setModelGroup(String modelGroup) {
        this.modelGroup = modelGroup;
    }

    public Date getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(Date bookedDate) {
        this.bookedDate = bookedDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectExport)) return false;

        ProjectExport that = (ProjectExport) o;

        if (projectId != that.projectId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (projectId ^ (projectId >>> 32));
    }
}
