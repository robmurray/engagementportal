package com.ys.eportal.model;

import java.util.Date;

/**
 * Created by rob on 4/5/15.
 */
public class Project {
    private long projectId;
    private String salesChannel;
    private long salesOrderNumber;
    private String accountTeam;
    private double orderAmount;
    private Date bookedDate;
    private String modelType;
    private String descriptionOfService;
    private int creditsUsed;

    public Project() {
    }

    public Project(long projectId) {
        this.projectId = projectId;
    }

    /*
    @TODO remove for test purposes only
     */
    public Project(long projectId, String salesChannel, long salesOrderNumber, String accountTeam, double orderAmount, Date bookedDate, String modelType, String descriptionOfService, int creditsUsed) {
        this.projectId = projectId;
        this.salesChannel = salesChannel;
        this.salesOrderNumber = salesOrderNumber;
        this.accountTeam = accountTeam;
        this.orderAmount = orderAmount;
        this.bookedDate = bookedDate;
        this.modelType = modelType;
        this.descriptionOfService = descriptionOfService;
        this.creditsUsed = creditsUsed;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getSalesChannel() {
        return salesChannel;
    }

    public void setSalesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
    }

    public long getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(long salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    public String getAccountTeam() {
        return accountTeam;
    }

    public void setAccountTeam(String accountTeam) {
        this.accountTeam = accountTeam;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(Date bookedDate) {
        this.bookedDate = bookedDate;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getDescriptionOfService() {
        return descriptionOfService;
    }

    public void setDescriptionOfService(String descriptionOfService) {
        this.descriptionOfService = descriptionOfService;
    }

    public int getCreditsUsed() {
        return creditsUsed;
    }

    public void setCreditsUsed(int creditsUsed) {
        this.creditsUsed = creditsUsed;
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
