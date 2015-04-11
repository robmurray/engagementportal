package com.ys.eportal.infra.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.*;

/**
 * Created by rob on 4/8/15.
 */
@Entity @IdClass(SalesOrderEntityId.class)
@javax.persistence.Table(name = "ep_SalesOrder")
public class SalesOrderEntity extends AbstractDomainBase{

    @Id @GeneratedValue
    @Column(name = "salesOrderId")
    private int salesOrderId;

    @Id
    @Column(name = "customerId")
    private int customerId;

    private String classRegSent;
    private String reportedRevRec;
    private int salesOrderNumber;
    private String status;
    private Timestamp bookDate;
    private Timestamp shipDate;
    private Timestamp planningMeetingDate;
    private Timestamp kickoffMeetingDate;
    private Timestamp onSiteStartDate;
    private Timestamp onSiteEndDate;
    private Timestamp releaseForRevenueRecDate;
    private String waitTime;
    private Integer bookedToKickOff;
    private Integer daysToClose;
    private BigDecimal amount;
    private String notes;
    private String location;
    private String region;
    private String modelGroup;
    private String service;
    private String accountTeam;
    private String remote;
    private String onsite;
    private String equipmentList;
    private String shawdow;

    public int getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(int salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "classRegSent")
    public String getClassRegSent() {
        return classRegSent;
    }

    public void setClassRegSent(String classRegSent) {
        this.classRegSent = classRegSent;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "reportedRevRec")
    public String getReportedRevRec() {
        return reportedRevRec;
    }

    public void setReportedRevRec(String reportedRevRec) {
        this.reportedRevRec = reportedRevRec;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "salesOrderNumber")
    public int getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(int salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "bookDate")
    public Timestamp getBookDate() {
        return bookDate;
    }

    public void setBookDate(Timestamp bookDate) {
        this.bookDate = bookDate;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "shipDate")
    public Timestamp getShipDate() {
        return shipDate;
    }

    public void setShipDate(Timestamp shipDate) {
        this.shipDate = shipDate;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "planningMeetingDate")
    public Timestamp getPlanningMeetingDate() {
        return planningMeetingDate;
    }

    public void setPlanningMeetingDate(Timestamp planningMeetingDate) {
        this.planningMeetingDate = planningMeetingDate;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "kickoffMeetingDate")
    public Timestamp getKickoffMeetingDate() {
        return kickoffMeetingDate;
    }

    public void setKickoffMeetingDate(Timestamp kickoffMeetingDate) {
        this.kickoffMeetingDate = kickoffMeetingDate;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "onSiteStartDate")
    public Timestamp getOnSiteStartDate() {
        return onSiteStartDate;
    }

    public void setOnSiteStartDate(Timestamp onSiteStartDate) {
        this.onSiteStartDate = onSiteStartDate;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "OnSiteEndDate")
    public Timestamp getOnSiteEndDate() {
        return onSiteEndDate;
    }

    public void setOnSiteEndDate(Timestamp onSiteEndDate) {
        this.onSiteEndDate = onSiteEndDate;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "releaseForRevenueRecDate")
    public Timestamp getReleaseForRevenueRecDate() {
        return releaseForRevenueRecDate;
    }

    public void setReleaseForRevenueRecDate(Timestamp releaseForRevenueRecDate) {
        this.releaseForRevenueRecDate = releaseForRevenueRecDate;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "waitTime")
    public String getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(String waitTime) {
        this.waitTime = waitTime;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "bookedToKickOff")
    public Integer getBookedToKickOff() {
        return bookedToKickOff;
    }

    public void setBookedToKickOff(Integer bookedToKickOff) {
        this.bookedToKickOff = bookedToKickOff;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "daysToClose")
    public Integer getDaysToClose() {
        return daysToClose;
    }

    public void setDaysToClose(Integer daysToClose) {
        this.daysToClose = daysToClose;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "notes")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "modelGroup")
    public String getModelGroup() {
        return modelGroup;
    }

    public void setModelGroup(String modelGroup) {
        this.modelGroup = modelGroup;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "service")
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "accountTeam")
    public String getAccountTeam() {
        return accountTeam;
    }

    public void setAccountTeam(String accountTeam) {
        this.accountTeam = accountTeam;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "remote")
    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "onsite")
    public String getOnsite() {
        return onsite;
    }

    public void setOnsite(String onsite) {
        this.onsite = onsite;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "equipmentList")
    public String getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(String equipmentList) {
        this.equipmentList = equipmentList;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "shawdow")
    public String getShawdow() {
        return shawdow;
    }

    public void setShawdow(String shawdow) {
        this.shawdow = shawdow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalesOrderEntity)) return false;

        SalesOrderEntity that = (SalesOrderEntity) o;

        if (customerId != that.customerId) return false;
        if (salesOrderId != that.salesOrderId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = salesOrderId;
        result = 31 * result + customerId;
        return result;
    }
}
