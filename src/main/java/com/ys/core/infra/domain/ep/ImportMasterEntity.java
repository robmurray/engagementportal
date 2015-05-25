package com.ys.core.infra.domain.ep;

import com.ys.core.infra.domain.AbstractDomainBase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by rob on 4/8/15.
 */
@Entity
@javax.persistence.Table(name = "import_Master")
public class ImportMasterEntity extends AbstractDomainBase {

    @Id @GeneratedValue
    private long importMasterId;
    private String salesOrderNumber;

    @Transient
    private CustomerEntity customer;
    private String classRegSent;
    private String reportedRevRec;
    private Integer credits;
    private String name;
    private String status;
    private Date bookDate;
    private Date shipDate;
    private Date planningMeetingDate;
    private Date kickoffMeetingDate;
    private Date onSiteStartDate;
    private Date onSiteEndDate;
    private Date releaseForRevenueRecDate;
    private String waitTime;
    private Integer bookedToKickOff;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "daysToClose")
    private Integer daysToClose;

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(String salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "amount")
    private BigDecimal amount;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "notes",columnDefinition = "TEXT")
    private String notes;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "location")
    private String location;


    @Basic(fetch = FetchType.EAGER)
    @javax.persistence.Column(name = "region")
    private String region;


    @Basic(fetch = FetchType.EAGER)
    @Column(name = "modelGroup")
    private String modelGroup;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "service",columnDefinition = "TEXT")
    private String service;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "accountTeam",columnDefinition = "TEXT")
    private String accountTeam;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "remote",columnDefinition = "TEXT")
    private String remote;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "onsite",columnDefinition = "TEXT")
    private String onsite;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "equipmentList",columnDefinition = "TEXT")
    private String equipmentList;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "shawdow",columnDefinition = "TEXT")
    private String shawdow;


    @Basic(fetch = FetchType.EAGER)
    @Column(name = "importProcessedDate")
    private Date importProcessedDate;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "importStatus")
    private String importStatus;

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public long getImportMasterId() {
        return importMasterId;
    }

    public void setImportMasterId(long importMasterId) {
        this.importMasterId = importMasterId;
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
    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "shipDate")
    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "planningMeetingDate")
    public Date getPlanningMeetingDate() {
        return planningMeetingDate;
    }

    public void setPlanningMeetingDate(Date planningMeetingDate) {
        this.planningMeetingDate = planningMeetingDate;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "kickoffMeetingDate")
    public Date getKickoffMeetingDate() {
        return kickoffMeetingDate;
    }

    public void setKickoffMeetingDate(Date kickoffMeetingDate) {
        this.kickoffMeetingDate = kickoffMeetingDate;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "onSiteStartDate")
    public Date getOnSiteStartDate() {
        return onSiteStartDate;
    }

    public void setOnSiteStartDate(Date onSiteStartDate) {
        this.onSiteStartDate = onSiteStartDate;
    }

    @Basic(fetch = FetchType.EAGER)
    @javax.persistence.Column(name = "OnSiteEndDate")
    public Date getOnSiteEndDate() {
        return onSiteEndDate;
    }

    public void setOnSiteEndDate(Date onSiteEndDate) {
        this.onSiteEndDate = onSiteEndDate;
    }

    @Basic(fetch = FetchType.EAGER)
    @javax.persistence.Column(name = "releaseForRevenueRecDate")
    public Date getReleaseForRevenueRecDate() {
        return releaseForRevenueRecDate;
    }

    public void setReleaseForRevenueRecDate(Date releaseForRevenueRecDate) {
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


    public Date getImportProcessedDate() {
        return importProcessedDate;
    }

    public void setImportProcessedDate(Date importProcessedDate) {
        this.importProcessedDate = importProcessedDate;
    }


    public String getImportStatus() {
        return importStatus;
    }

    public void setImportStatus(String importStatus) {
        this.importStatus = importStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImportMasterEntity)) return false;

        ImportMasterEntity that = (ImportMasterEntity) o;

        if (importMasterId != that.importMasterId) return false;
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
        if (salesOrderNumber != null ? !salesOrderNumber.equals(that.salesOrderNumber) : that.salesOrderNumber != null)
            return false;
        if (service != null ? !service.equals(that.service) : that.service != null) return false;
        if (shawdow != null ? !shawdow.equals(that.shawdow) : that.shawdow != null) return false;
        if (shipDate != null ? !shipDate.equals(that.shipDate) : that.shipDate != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (waitTime != null ? !waitTime.equals(that.waitTime) : that.waitTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (importMasterId ^ (importMasterId >>> 32));
        result = 31 * result + (salesOrderNumber != null ? salesOrderNumber.hashCode() : 0);
        result = 31 * result + (classRegSent != null ? classRegSent.hashCode() : 0);
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
}
