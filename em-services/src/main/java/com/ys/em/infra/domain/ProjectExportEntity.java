package com.ys.em.infra.domain;

import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by rob on 4/5/15.
 */
@Entity
@Table(name = "export_epdata")
public class ProjectExportEntity extends AbstractEntity {

    @Id
    @Basic(fetch = FetchType.EAGER)
    private Long projectId;

    @Basic(fetch = FetchType.EAGER)
    private Long credits;

    @Basic(fetch = FetchType.EAGER)
    private Long bookedToKickOff;

    @Basic(fetch = FetchType.EAGER)
    private Long daysToClose;

    @Basic(fetch = FetchType.EAGER)
    private String location;

    @Basic(fetch = FetchType.EAGER)
    private long salesOrderId;

    @Basic(fetch = FetchType.EAGER)
    private long customerId;

    @Basic(fetch = FetchType.EAGER)
    private String name;

    @Basic(fetch = FetchType.EAGER)
    private String modelGroup;

    @Basic(fetch = FetchType.EAGER)
    private Date bookedDate;

    @Basic(fetch = FetchType.EAGER)
    private String health;

    @Basic(fetch = FetchType.EAGER)
    private String classRegSent;

    @Basic(fetch = FetchType.EAGER)
    private String reportedRevRec;

    @Basic(fetch = FetchType.EAGER)
    private String service;

    @Basic(fetch = FetchType.EAGER)
    private String status;

    @Basic(fetch = FetchType.EAGER)
    private String waitTime;

    @Basic(fetch = FetchType.EAGER)
    private Date activityDate;

    @Column(columnDefinition="default '0'")
    @Basic(fetch = FetchType.EAGER)
    private Long activityMonthNumber;

    @Basic(fetch = FetchType.EAGER)
    private Long activityYear;

    @Basic(fetch = FetchType.EAGER)
    private BigDecimal amount;

    @Basic(fetch = FetchType.EAGER)
    private String btCustomerName;

    @Basic(fetch = FetchType.EAGER)
    private String contractStatusCode;

    @Basic(fetch = FetchType.EAGER)
    private String endUserName;

    @Basic(fetch = FetchType.EAGER)
    private String forecastGroupCode;

    @Basic(fetch = FetchType.EAGER)
    private Double orderedQuantity;

    @Basic(fetch = FetchType.EAGER)
    private String productFamilyCode;

    @Basic(fetch = FetchType.EAGER)
    private String region;

    @Basic(fetch = FetchType.EAGER)
    private String salesOrderNumber;

    @Basic(fetch = FetchType.EAGER)
    private String stChannelName;

    @Basic(fetch = FetchType.EAGER)
    private String stSalesAgentName;

    public ProjectExportEntity() {
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getCredits() {
        return credits;
    }

    public void setCredits(Long credits) {
        this.credits = credits;
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(String waitTime) {
        this.waitTime = waitTime;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public Long getActivityMonthNumber() {
        return activityMonthNumber;
    }

    public void setActivityMonthNumber(Long activityMonthNumber) {
        this.activityMonthNumber = activityMonthNumber;
    }

    public Long getActivityYear() {
        return activityYear;
    }

    public void setActivityYear(Long activityYear) {
        this.activityYear = activityYear;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBtCustomerName() {
        return btCustomerName;
    }

    public void setBtCustomerName(String btCustomerName) {
        this.btCustomerName = btCustomerName;
    }

    public String getContractStatusCode() {
        return contractStatusCode;
    }

    public void setContractStatusCode(String contractStatusCode) {
        this.contractStatusCode = contractStatusCode;
    }

    public String getEndUserName() {
        return endUserName;
    }

    public void setEndUserName(String endUserName) {
        this.endUserName = endUserName;
    }

    public String getForecastGroupCode() {
        return forecastGroupCode;
    }

    public void setForecastGroupCode(String forecastGroupCode) {
        this.forecastGroupCode = forecastGroupCode;
    }

    public Double getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(Double orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public String getProductFamilyCode() {
        return productFamilyCode;
    }

    public void setProductFamilyCode(String productFamilyCode) {
        this.productFamilyCode = productFamilyCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(String salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    public String getStChannelName() {
        return stChannelName;
    }

    public void setStChannelName(String stChannelName) {
        this.stChannelName = stChannelName;
    }

    public String getStSalesAgentName() {
        return stSalesAgentName;
    }

    public void setStSalesAgentName(String stSalesAgentName) {
        this.stSalesAgentName = stSalesAgentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectExportEntity)) return false;

        ProjectExportEntity that = (ProjectExportEntity) o;

        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return projectId != null ? projectId.hashCode() : 0;
    }
}
