package com.ys.eportal.model;

import com.ys.eportal.infra.domain.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by rob on 4/5/15.
 */
public class Project extends AbstractModelBase {

    private long projectId;
    private boolean readonly;

    private List<SalesOrder> salesOrders;
    private List<Resource> remoteResources;
    private List<Resource> accountResources;
    private List<Resource> onsiteResources;
    private List<Activity> activities;

    private String name;
    private List<String> statusValues = Constants.Projects.statusValues;
    private String status;
    private String service;
    private Long bookedToKickOff;
    private Long daysToClose;
    private String waitTime;
    private List<String> waitTimeValues = Constants.Projects.waitTimeValues;
    private String location;
    private List<String> healthStatusValues = Constants.Projects.healthStatusValues;
    private String healthStatus;
    private List<NoteInterface> notes;


    public Project() {
    }


    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }



    public void addActivity(Activity activity) {
        if (this.activities == null) {
            this.activities = new ArrayList<Activity>();
        }
        if (activity == null) {
            return;
        }
        this.activities.add(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<SalesOrder> getSalesOrders() {
        return salesOrders;
    }

    public void setSalesOrders(List<SalesOrder> salesorders) {
        this.salesOrders = salesorders;
    }

    public void addSalesOrder(SalesOrder salesOrder) {
        if (this.salesOrders == null) {
            this.salesOrders = new ArrayList<SalesOrder>();
        }
        if (salesOrder == null) {
            return;
        }
        this.salesOrders.add(salesOrder);
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }



    public SalesOrder getFirstSalesOrder() {
        SalesOrder so = null;
        if (this.salesOrders != null && this.salesOrders.size() > 0) {
            so = this.salesOrders.get(0);
        }
        return so;
    }

    public List<Resource> getRemoteResources() {
        return remoteResources;
    }

    public void setRemoteResources(List<Resource> remoteResources) {
        this.remoteResources = remoteResources;
    }

    public List<Resource> getAccountResources() {
        return accountResources;
    }

    public void setAccountResources(List<Resource> accountResources) {
        this.accountResources = accountResources;
    }

    public List<Resource> getOnsiteResources() {
        return onsiteResources;
    }

    public void setOnsiteResources(List<Resource> onsiteResources) {
        this.onsiteResources = onsiteResources;
    }

    public void addNotes(NoteInterface note) {
        if (this.notes == null) {
            notes = new ArrayList<NoteInterface>();
        }
        if (note == null) {
            return;
        }
        notes.add(note);
    }

    public void addRemoteResource(Resource resource) {
        if (resource == null) {
            return;
        }
        if (this.remoteResources == null) {
            this.remoteResources = new ArrayList<Resource>();
        }
        this.remoteResources.add(resource);
    }

    public void addAccountResource(Resource resource) {
        if (resource == null) {
            return;
        }
        if (this.accountResources == null) {
            this.accountResources = new ArrayList<Resource>();
        }
        this.accountResources.add(resource);
    }

    public void addOnsiteResource(Resource resource) {
        if (resource == null) {
            return;
        }
        if (this.onsiteResources == null) {
            this.onsiteResources = new ArrayList<Resource>();
        }
        this.onsiteResources.add(resource);
    }


    public List<NoteInterface> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteInterface> notes) {
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<String> getWaitTimeValues() {
        return waitTimeValues;
    }

    public void setWaitTimeValues(List<String> waitTimeValues) {
        this.waitTimeValues = waitTimeValues;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getHealthStatusValues() {
        return healthStatusValues;
    }

    public void setHealthStatusValues(List<String> healthStatusValues) {
        this.healthStatusValues = healthStatusValues;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;

        Project project = (Project) o;

        if (projectId != project.projectId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (projectId ^ (projectId >>> 32));
    }
}
