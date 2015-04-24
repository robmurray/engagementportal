package com.ys.eportal.model;

import com.ys.eportal.infra.domain.Constants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by rob on 4/5/15.
 */
public class Project {

    private int salesOrderId;
    @Size(min=1, max=30)
    private String salesOrderNumber;
    private Customer customer;
    @NotNull
    private Integer customerId;

    private int credits;
    private String creditStatus;
    private long importControlId;
    private String classRegSent;
    private String reportedRevRec;


    private List<String> statusValues = Constants.SalesOrders.statusValues;

    private String status;

    private Map<String,String> statusDisplayClass = new HashMap<String,String>(){{
        put("","danger");
        put(Constants.SalesOrders.STATUS_BOOKED,"info");
        put(Constants.SalesOrders.STATUS_COMPLETE,"primary");
        put(Constants.SalesOrders.STATUS_INPROCESS,"success");
        put(Constants.SalesOrders.STATUS_NOTDEFINED,"danger");
        put(Constants.SalesOrders.STATUS_POSTSUPPORT,"danger");
        put(Constants.SalesOrders.STATUS_PROPOSED,"default");
        put(Constants.SalesOrders.STATUS_RANDSUPPORT,"warning");
        put(Constants.SalesOrders.STATUS_SCHEDULED,"success");
    }};
    private Date bookDate;
    private Date shipDate;
    private Date planningMeetingDate;
    private Date kickoffMeetingDate;
    private Date onSiteStartDate;
    private Date onSiteEndDate;
    private Date releaseForRevenueRecDate;

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


    public Project() {
    }

    public int getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(int salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Project(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public long getImportControlId() {
        return importControlId;
    }

    public void setImportControlId(long importControlId) {
        this.importControlId = importControlId;
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


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public Date getPlanningMeetingDate() {
        return planningMeetingDate;
    }

    public void setPlanningMeetingDate(Date planningMeetingDate) {
        this.planningMeetingDate = planningMeetingDate;
    }

    public Date getKickoffMeetingDate() {
        return kickoffMeetingDate;
    }

    public void setKickoffMeetingDate(Date kickoffMeetingDate) {
        this.kickoffMeetingDate = kickoffMeetingDate;
    }

    public Date getOnSiteStartDate() {
        return onSiteStartDate;
    }

    public void setOnSiteStartDate(Date onSiteStartDate) {
        this.onSiteStartDate = onSiteStartDate;
    }

    public Date getOnSiteEndDate() {
        return onSiteEndDate;
    }

    public void setOnSiteEndDate(Date onSiteEndDate) {
        this.onSiteEndDate = onSiteEndDate;
    }

    public Date getReleaseForRevenueRecDate() {
        return releaseForRevenueRecDate;
    }

    public void setReleaseForRevenueRecDate(Date releaseForRevenueRecDate) {
        this.releaseForRevenueRecDate = releaseForRevenueRecDate;
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

    public void setSalesOrderNumber(String salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }


    public String getStatusDisplayClass(){
        if(status==null){
            status ="";
        }
        return this.statusDisplayClass.get(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;

        Project project = (Project) o;

        if (salesOrderId != project.salesOrderId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return salesOrderId;
    }
}
