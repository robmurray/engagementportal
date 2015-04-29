package com.ys.eportal.infra.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rob on 4/8/15.
 */
@Entity
@Table(name = "ep_Project")
public class ProjectEntity extends AbstractDomainBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "projectId")
    private long projectId;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "name", unique = true)
    private String name;

/*
    this relation is stored at the so level
    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;
*/

    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SalesOrderEntity salesOrders;

    @OrderBy("createDate DESC")
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProjectNotesEntity> notes = new HashSet<ProjectNotesEntity>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProjectResourceEntity> projectResources;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProjectActivityEntity> projectActivity;


    @Basic(fetch = FetchType.EAGER)
    @Column(name = "contact")
    private String contact;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "location")
    private String location;

    private Integer credits;
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

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "health")
    private String health = "good";

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "waitTime")
    private String waitTime;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "bookDate")
    private Date bookDate;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "shipDate")
    private Date shipDate;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "planningMeetingDate")
    private Date planningMeetingDate;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "kickOffMeetingDate")
    private Date kickoffMeetingDate;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "onSiteStartDate")
    private Date onSiteStartDate;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "onSiteEndDate")
    private Date onSiteEndDate;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "releaseForRevenueRecDate")
    private Date releaseForRevenueRecDate;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "bookedToKickOff")
    private Integer bookedToKickOff;


    @Basic(fetch = FetchType.EAGER)
    @Column(name = "daysToClose")
    private Integer daysToClose;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "service", columnDefinition = "TEXT")
    private String service;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "accountTeam", columnDefinition = "TEXT")
    private String accountTeam;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "remote", columnDefinition = "TEXT")
    private String remote;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "onsite", columnDefinition = "TEXT")


    private String onsite;


    public ProjectEntity() {
    }

    public Set<ProjectResourceEntity> getProjectResources() {
        return projectResources;
    }

    public void setProjectResources(Set<ProjectResourceEntity> projectResources) {
        this.projectResources = projectResources;
    }

    public Set<ProjectActivityEntity> getProjectActivity() {
        return projectActivity;
    }

    public void setProjectActivity(Set<ProjectActivityEntity> projectActivity) {
        this.projectActivity = projectActivity;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
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

    public Set<ProjectNotesEntity> getNotes() {
        return notes;
    }

    public void setNotes(Set<ProjectNotesEntity> notes) {
        this.notes = notes;
    }

    public String getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(String waitTime) {
        this.waitTime = waitTime;
    }



    public SalesOrderEntity getSalesOrders() {
        return salesOrders;
    }

    public void setSalesOrders(SalesOrderEntity salesOrders) {
        this.salesOrders = salesOrders;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public String getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectEntity)) return false;

        ProjectEntity that = (ProjectEntity) o;

        if (projectId != that.projectId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (projectId ^ (projectId >>> 32));
    }
}
