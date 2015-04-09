package com.ys.eportal.infra.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by rob on 4/8/15.
 */
@Entity
@javax.persistence.Table(name = "import_Master")
public class ImportMaster  extends AbstractDomainBase {

    @Id @GeneratedValue
    private long ImportMasterId;

    private String classRegSent;
    private String reportedRevRec;
    private Integer credits;
    private String name;
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
    private Timestamp importProcessedDate;
    private String importStatus;

    public long getImportMasterId() {
        return ImportMasterId;
    }

    public void setImportMasterId(long importMasterId) {
        ImportMasterId = importMasterId;
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
    @Column(name = "credits")
    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @javax.persistence.Column(name = "OnSiteEndDate")
    public Timestamp getOnSiteEndDate() {
        return onSiteEndDate;
    }

    public void setOnSiteEndDate(Timestamp onSiteEndDate) {
        this.onSiteEndDate = onSiteEndDate;
    }

    @Basic(fetch = FetchType.EAGER)
    @javax.persistence.Column(name = "releaseForRevenueRecDate")
    public Timestamp getReleaseForRevenueRecDate() {
        return releaseForRevenueRecDate;
    }

    public void setReleaseForRevenueRecDate(Timestamp releaseForRevenueRecDate) {
        this.releaseForRevenueRecDate = releaseForRevenueRecDate;
    }

    @Basic(fetch = FetchType.EAGER)
    @javax.persistence.Column(name = "waitTime")
    public String getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(String waitTime) {
        this.waitTime = waitTime;
    }

    @Basic(fetch = FetchType.EAGER)
    @javax.persistence.Column(name = "bookedToKickOff")
    public Integer getBookedToKickOff() {
        return bookedToKickOff;
    }

    public void setBookedToKickOff(Integer bookedToKickOff) {
        this.bookedToKickOff = bookedToKickOff;
    }

    @Basic(fetch = FetchType.EAGER)
    @javax.persistence.Column(name = "daysToClose")
    public Integer getDaysToClose() {
        return daysToClose;
    }

    public void setDaysToClose(Integer daysToClose) {
        this.daysToClose = daysToClose;
    }

    @Basic(fetch = FetchType.EAGER)
    @javax.persistence.Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Basic(fetch = FetchType.EAGER)
    @javax.persistence.Column(name = "notes")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Basic(fetch = FetchType.EAGER)
    @javax.persistence.Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic(fetch = FetchType.EAGER)
    @javax.persistence.Column(name = "region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic(fetch = FetchType.EAGER)
    @javax.persistence.Column(name = "modelGroup")
    public String getModelGroup() {
        return modelGroup;
    }

    public void setModelGroup(String modelGroup) {
        this.modelGroup = modelGroup;
    }

    @Basic(fetch = FetchType.EAGER)
    @javax.persistence.Column(name = "service")
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Basic(fetch = FetchType.EAGER)
    @javax.persistence.Column(name = "accountTeam")
    public String getAccountTeam() {
        return accountTeam;
    }

    public void setAccountTeam(String accountTeam) {
        this.accountTeam = accountTeam;
    }

    @Basic(fetch = FetchType.EAGER)
    @javax.persistence.Column(name = "remote")
    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    @Basic(fetch = FetchType.EAGER)
    @javax.persistence.Column(name = "onsite")
    public String getOnsite() {
        return onsite;
    }

    public void setOnsite(String onsite) {
        this.onsite = onsite;
    }

    @Basic(fetch = FetchType.EAGER)
    @javax.persistence.Column(name = "equipmentList")
    public String getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(String equipmentList) {
        this.equipmentList = equipmentList;
    }

    @Basic(fetch = FetchType.EAGER)
    @javax.persistence.Column(name = "shawdow")
    public String getShawdow() {
        return shawdow;
    }

    public void setShawdow(String shawdow) {
        this.shawdow = shawdow;
    }


    @Basic(fetch = FetchType.EAGER)
    @javax.persistence.Column(name = "importProcessedDate")
    public Timestamp getImportProcessedDate() {
        return importProcessedDate;
    }

    public void setImportProcessedDate(Timestamp importProcessedDate) {
        this.importProcessedDate = importProcessedDate;
    }

    @Basic(fetch = FetchType.EAGER)
    @javax.persistence.Column(name = "importStatus")
    public String getImportStatus() {
        return importStatus;
    }

    public void setImportStatus(String importStatus) {
        this.importStatus = importStatus;
    }

    @Override
    public int hashCode() {
        int result = classRegSent != null ? classRegSent.hashCode() : 0;
        result = 31 * result + (reportedRevRec != null ? reportedRevRec.hashCode() : 0);
        result = 31 * result + (credits != null ? credits.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (bookDate != null ? bookDate.hashCode() : 0);
        result = 31 * result + (shipDate != null ? shipDate.hashCode() : 0);
        result = 31 * result + (planningMeetingDate != null ? planningMeetingDate.hashCode() : 0);
        result = 31 * result + (kickoffMeetingDate != null ? kickoffMeetingDate.hashCode() : 0);
        result = 31 * result + (onSiteStartDate != null ? onSiteStartDate.hashCode() : 0);
        result = 31 * result + (onSiteEndDate != null ? onSiteEndDate.hashCode() : 0);
        result = 31 * result + (releaseForRevenueRecDate != null ? releaseForRevenueRecDate.hashCode() : 0);
        result = 31 * result + (waitTime != null ? waitTime.hashCode() : 0);
        result = 31 * result + (bookedToKickOff != null ? bookedToKickOff.hashCode() : 0);
        result = 31 * result + (daysToClose != null ? daysToClose.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (modelGroup != null ? modelGroup.hashCode() : 0);
        result = 31 * result + (service != null ? service.hashCode() : 0);
        result = 31 * result + (accountTeam != null ? accountTeam.hashCode() : 0);
        result = 31 * result + (remote != null ? remote.hashCode() : 0);
        result = 31 * result + (onsite != null ? onsite.hashCode() : 0);
        result = 31 * result + (equipmentList != null ? equipmentList.hashCode() : 0);
        result = 31 * result + (shawdow != null ? shawdow.hashCode() : 0);
        result = 31 * result + (importProcessedDate != null ? importProcessedDate.hashCode() : 0);
        result = 31 * result + (importStatus != null ? importStatus.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImportMaster that = (ImportMaster) o;

        if (accountTeam != null ? !accountTeam.equals(that.accountTeam) : that.accountTeam != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (bookDate != null ? !bookDate.equals(that.bookDate) : that.bookDate != null) return false;
        if (bookedToKickOff != null ? !bookedToKickOff.equals(that.bookedToKickOff) : that.bookedToKickOff != null)
            return false;
        if (classRegSent != null ? !classRegSent.equals(that.classRegSent) : that.classRegSent != null) return false;
        if (credits != null ? !credits.equals(that.credits) : that.credits != null) return false;
        if (daysToClose != null ? !daysToClose.equals(that.daysToClose) : that.daysToClose != null) return false;
        if (equipmentList != null ? !equipmentList.equals(that.equipmentList) : that.equipmentList != null)
            return false;
        if (importProcessedDate != null ? !importProcessedDate.equals(that.importProcessedDate) : that.importProcessedDate != null)
            return false;
        if (importStatus != null ? !importStatus.equals(that.importStatus) : that.importStatus != null) return false;
        if (kickoffMeetingDate != null ? !kickoffMeetingDate.equals(that.kickoffMeetingDate) : that.kickoffMeetingDate != null)
            return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (modelGroup != null ? !modelGroup.equals(that.modelGroup) : that.modelGroup != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (onSiteEndDate != null ? !onSiteEndDate.equals(that.onSiteEndDate) : that.onSiteEndDate != null)
            return false;
        if (onSiteStartDate != null ? !onSiteStartDate.equals(that.onSiteStartDate) : that.onSiteStartDate != null)
            return false;
        if (onsite != null ? !onsite.equals(that.onsite) : that.onsite != null) return false;
        if (planningMeetingDate != null ? !planningMeetingDate.equals(that.planningMeetingDate) : that.planningMeetingDate != null)
            return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (releaseForRevenueRecDate != null ? !releaseForRevenueRecDate.equals(that.releaseForRevenueRecDate) : that.releaseForRevenueRecDate != null)
            return false;
        if (remote != null ? !remote.equals(that.remote) : that.remote != null) return false;
        if (reportedRevRec != null ? !reportedRevRec.equals(that.reportedRevRec) : that.reportedRevRec != null)
            return false;
        if (service != null ? !service.equals(that.service) : that.service != null) return false;
        if (shawdow != null ? !shawdow.equals(that.shawdow) : that.shawdow != null) return false;
        if (shipDate != null ? !shipDate.equals(that.shipDate) : that.shipDate != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (waitTime != null ? !waitTime.equals(that.waitTime) : that.waitTime != null) return false;

        return true;
    }
}
