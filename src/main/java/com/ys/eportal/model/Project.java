package com.ys.eportal.model;

import com.ys.eportal.infra.domain.Constants;

import java.util.*;

/**
 * Created by rob on 4/5/15.
 */
public class Project extends AbstractModelBase{

    private long projectId;

    private String name;

    private List<SalesOrder> salesOrders;

    private List<Resource> remoteResources;
    private List<Resource> accountResources;
    private List<Resource> onsiteResources;

    //@Size(min=1, max=30)
    // private String salesOrderNumber;

    private List<String> statusValues = Constants.Projects.statusValues;

    private String status;

    private Map<String,String> statusDisplayClass = new HashMap<String,String>(){{
        put("","danger");
        put(Constants.Projects.STATUS_BOOKED,"info");
        put(Constants.Projects.STATUS_COMPLETE,"primary");
        put(Constants.Projects.STATUS_INPROCESS,"success");
        put(Constants.Projects.STATUS_NOTDEFINED,"danger");
        put(Constants.Projects.STATUS_POSTSUPPORT,"danger");
        put(Constants.Projects.STATUS_PROPOSED,"default");
        put(Constants.Projects.STATUS_RANDSUPPORT,"warning");
        put(Constants.Projects.STATUS_SCHEDULED,"success");
    }};

    private String healthStatus;
    private List<String> healthStatusValues = new ArrayList<String>() {{
        add("green");
        add("yellow");
        add("orange");
        add("red");
    }};
    private List<Activity> activities;

    private List<String> waitTimeValues = new ArrayList<String>() {{
        add("standard");
        add("customer");
        add("resource");
    }};

    private List<NoteInterface> notes;

    private String waitTime;

    private Integer bookedToKickOff;
    private Integer daysToClose;


    private String location;

    private String modelGroup;
    private List<String> modelGroupValues = new ArrayList<String>() {{
        add("WLAN");
        add("VPM");
        add("CSN");
    }};


    private String service;



    private String accountTeam;
    private String remote;
    private String onsite;


    public Project() {
    }


    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }
    public void addActivity(Activity activity){
        if(this.activities ==null){
            this.activities = new ArrayList<Activity>();
        }
        if(activity==null){
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

    public void addSalesOrder(SalesOrder salesOrder){
        if(this.salesOrders ==null){
            this.salesOrders = new ArrayList<SalesOrder>();
        }
        if(salesOrder==null){
            return;
        }
        this.salesOrders.add(salesOrder);
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public List<String> getHealthStatusValues() {
        return healthStatusValues;
    }

    public void setHealthStatusValues(List<String> healthStatusValues) {
        this.healthStatusValues = healthStatusValues;
    }

    public void setSalesOrders(List<SalesOrder> salesorders) {
        this.salesOrders = salesorders;
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

    public Map<String, String> getStatusDisplayClass() {
        return statusDisplayClass;
    }

    public void setStatusDisplayClass(Map<String, String> statusDisplayClass) {
        this.statusDisplayClass = statusDisplayClass;
    }

    public List<String> getWaitTimeValues() {
        return waitTimeValues;
    }

    public void setWaitTimeValues(List<String> waitTimeValues) {
        this.waitTimeValues = waitTimeValues;
    }

    public String getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(String waitTime) {
        this.waitTime = waitTime;
    }

    public Integer getBookedToKickOff() {
        return bookedToKickOff;
    }

    public void setBookedToKickOff(Integer bookedToKickOff) {
        this.bookedToKickOff = bookedToKickOff;
    }

    public Integer getDaysToClose() {
        return daysToClose;
    }

    public void setDaysToClose(Integer daysToClose) {
        this.daysToClose = daysToClose;
    }

    public void addNotes(NoteInterface note){
        if(this.notes == null){
            notes = new ArrayList<NoteInterface>();
        }
        if(note==null){
            return;
        }
        notes.add(note);
    }

    public void addRemoteResource(Resource resource){
        if(resource ==null){
            return;
        }
        if(this.remoteResources==null){
            this.remoteResources= new ArrayList<Resource>();
        }
        this.remoteResources.add(resource);
    }

    public void addAccountResource(Resource resource){
        if(resource ==null){
            return;
        }
        if(this.accountResources==null){
            this.accountResources= new ArrayList<Resource>();
        }
        this.accountResources.add(resource);
    }

    public void addOnsiteResource(Resource resource){
        if(resource ==null){
            return;
        }
        if(this.onsiteResources==null){
            this.onsiteResources= new ArrayList<Resource>();
        }
        this.onsiteResources.add(resource);
    }


    public List<NoteInterface> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteInterface> notes) {
        this.notes = notes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getAccountTeam() {
        return accountTeam;
    }

    public void setAccountTeam(String accountTeam) {
        this.accountTeam = accountTeam;
    }

    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    public String getOnsite() {
        return onsite;
    }

    public void setOnsite(String onsite) {
        this.onsite = onsite;
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
