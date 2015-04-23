package com.ys.eportal.infra.domain;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by rob on 4/8/15.
 */
@Entity
@Table(name = "ep_SalesOrder")
public class SalesOrderEntity extends AbstractDomainBase {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "salesOrderId")
    private int salesOrderId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    private String salesOrderNumber;

    private long importControlId;
    private int credits;
    private String creditStatus;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "classRegSent")
    private String classRegSent;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "reportedRevRec")
    private String reportedRevRec;


    @Basic(fetch = FetchType.EAGER)
    @Column(name = "status")
    private String status = "undefined";
    private Date bookDate;
    private Date shipDate;
    private Date planningMeetingDate;
    private Date kickoffMeetingDate;
    private Date onSiteStartDate;
    private Date onSiteEndDate;
    private Date releaseForRevenueRecDate;
    private String waitTime;
    private Integer bookedToKickOff;
    private Integer daysToClose;
    private BigDecimal amount;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
    private String location;
    private String region;
    private String modelGroup;
    private String service;
    private String accountTeam;
    private String remote;
    private String onsite;

    public SalesOrderEntity() {


    }

    public String getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
    }

    public void setSalesOrderId(int salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public int getSalesOrderId() {
        return salesOrderId;
    }

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(String salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }


    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public long getImportControlId() {
        return importControlId;
    }

    public void setImportControlId(long importControlId) {
        this.importControlId = importControlId;
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
    @Column(name = "OnSiteEndDate")
    public Date getOnSiteEndDate() {
        return onSiteEndDate;
    }

    public void setOnSiteEndDate(Date onSiteEndDate) {
        this.onSiteEndDate = onSiteEndDate;
    }

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "releaseForRevenueRecDate")
    public Date getReleaseForRevenueRecDate() {
        return releaseForRevenueRecDate;
    }

    public void setReleaseForRevenueRecDate(Date releaseForRevenueRecDate) {
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
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "service", columnDefinition = "TEXT")
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "accountTeam", columnDefinition = "TEXT")
    public String getAccountTeam() {
        return accountTeam;
    }

    public void setAccountTeam(String accountTeam) {
        this.accountTeam = accountTeam;
    }
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "remote", columnDefinition = "TEXT")
    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "onsite", columnDefinition = "TEXT")
    public String getOnsite() {
        return onsite;
    }

    public void setOnsite(String onsite) {
        this.onsite = onsite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalesOrderEntity)) return false;

        SalesOrderEntity that = (SalesOrderEntity) o;

        if (salesOrderId != that.salesOrderId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return salesOrderId;
    }
}