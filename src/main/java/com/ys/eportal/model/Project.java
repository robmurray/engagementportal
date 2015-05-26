package com.ys.eportal.model;

import com.ys.core.infra.domain.ep.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rob on 4/5/15.
 */
public class Project extends AbstractModelBase {

    private long projectId;
    private boolean readonly;
    private long credits;
    private SalesOrder salesOrder;
    private List<Resource> projectResources;
    private List<Resource> availableResources;
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

    private List<String> resourceRoleValues = Constants.ResourceRole.typeValues;


    public Project() {
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


    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
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

    public List<Resource> getAvailableResources() {
        return availableResources;
    }

    public void setAvailableResources(List<Resource> availableResources) {
        this.availableResources = availableResources;
    }
    public void addAvailableResource(Resource resource){
        if(this.availableResources == null){
            this.availableResources = new ArrayList<Resource>();
        }
        this.availableResources.add(resource);
    }
    public List<Resource> getProjectResources() {
        return projectResources;
    }

    public void setProjectResources(List<Resource> projectResources) {
        this.projectResources = projectResources;
    }

    public void addResource(Resource resource){
        if(this.projectResources == null){
            this.projectResources = new ArrayList<Resource>();
        }
        this.projectResources.add(resource);
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

    public List<String> getResourceRoleValues() {
        return resourceRoleValues;
    }

    public void setResourceRoleValues(List<String> resourceRoleValues) {
        this.resourceRoleValues = resourceRoleValues;
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
