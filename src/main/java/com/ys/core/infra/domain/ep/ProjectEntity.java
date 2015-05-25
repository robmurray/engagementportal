package com.ys.core.infra.domain.ep;

import com.ys.core.infra.domain.AbstractDomainBase;

import javax.persistence.*;
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
    @Column(name = "credits")
    private long credits;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "location")
    private String location;


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


    public void setCredits(long credits) {
        this.credits = credits;
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

    public void addNotes(ProjectNotesEntity pne){
        if(this.notes ==null){
            this.notes = new HashSet<ProjectNotesEntity>();
        }
        notes.add(pne);
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


    public long getCredits() {
        return credits;
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
