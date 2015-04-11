package com.ys.eportal.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rob on 4/5/15.
 */
public class Project {
    private int projectId;
    private Customer customer;
    private String classRegSent;
    private String reportedRevRec;
    private int salesOrderNumber;


    private List<String> statusValues = new ArrayList<String>() {
        {
            add("Complete");
            add("Scheduled");
            add("R&D Support");
            add("In Process");
            add("Post support");
            add("Booked");
            add("Proposed");
        }
    };

    private String status;
    private Timestamp bookDate;
    private Timestamp shipDate;
    private Timestamp planningMeetingDate;
    private Timestamp kickoffMeetingDate;
    private Timestamp onSiteStartDate;
    private Timestamp onSiteEndDate;
    private Timestamp releaseForRevenueRecDate;

    private List<String> waitTimeValues = new ArrayList<String>() {{
        add("standard");
        add("customer");
        add("resource");
    }};

    private String waitTime;

    private Integer bookedToKickOff;
    private Integer daysToClose;
    private BigDecimal amount;
    private String notes;
    private String location;
    private String region;
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
    private String equipmentList;
    private String shawdow;


    public Project() {
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<String> getStatusValues() {
        return statusValues;
    }

    public void setStatusValues(List<String> statusValues) {
        this.statusValues = statusValues;
    }

    public List<String> getWaitTimeValues() {
        return waitTimeValues;
    }

    public List<String> getModelGroupValues() {
        return modelGroupValues;
    }

    public void setModelGroupValues(List<String> modelGroupValues) {
        this.modelGroupValues = modelGroupValues;
    }

    public void setWaitTimeValues(List<String> waitTimeValues) {
        this.waitTimeValues = waitTimeValues;
    }

    public String getClassRegSent() {
        return classRegSent;
    }

    public void setClassRegSent(String classRegSent) {
        this.classRegSent = classRegSent;
    }

    public String getReportedRevRec() {
        return reportedRevRec;
    }

    public void setReportedRevRec(String reportedRevRec) {
        this.reportedRevRec = reportedRevRec;
    }

    public int getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(int salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getBookDate() {
        return bookDate;
    }

    public void setBookDate(Timestamp bookDate) {
        this.bookDate = bookDate;
    }

    public Timestamp getShipDate() {
        return shipDate;
    }

    public void setShipDate(Timestamp shipDate) {
        this.shipDate = shipDate;
    }

    public Timestamp getPlanningMeetingDate() {
        return planningMeetingDate;
    }

    public void setPlanningMeetingDate(Timestamp planningMeetingDate) {
        this.planningMeetingDate = planningMeetingDate;
    }

    public Timestamp getKickoffMeetingDate() {
        return kickoffMeetingDate;
    }

    public void setKickoffMeetingDate(Timestamp kickoffMeetingDate) {
        this.kickoffMeetingDate = kickoffMeetingDate;
    }

    public Timestamp getOnSiteStartDate() {
        return onSiteStartDate;
    }

    public void setOnSiteStartDate(Timestamp onSiteStartDate) {
        this.onSiteStartDate = onSiteStartDate;
    }

    public Timestamp getOnSiteEndDate() {
        return onSiteEndDate;
    }

    public void setOnSiteEndDate(Timestamp onSiteEndDate) {
        this.onSiteEndDate = onSiteEndDate;
    }

    public Timestamp getReleaseForRevenueRecDate() {
        return releaseForRevenueRecDate;
    }

    public void setReleaseForRevenueRecDate(Timestamp releaseForRevenueRecDate) {
        this.releaseForRevenueRecDate = releaseForRevenueRecDate;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getModelGroup() {
        return modelGroup;
    }

    public void setModelGroup(String modelGroup) {
        this.modelGroup = modelGroup;
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

    public String getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(String equipmentList) {
        this.equipmentList = equipmentList;
    }

    public String getShawdow() {
        return shawdow;
    }

    public void setShawdow(String shawdow) {
        this.shawdow = shawdow;
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
        return projectId;
    }
}
