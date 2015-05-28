package com.ys.em.infra.domain;

import com.ys.common.infra.domain.AbstractDomainBase;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rob on 4/26/15.
 */
@Entity
@Table(name = "ep_ProjectActivity")
public class ProjectActivityEntity extends AbstractDomainBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "activityId")
    private long activityId;

    @ManyToOne
    @Basic(fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId", nullable = false)
    private ProjectEntity project;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "name", unique = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "date", unique = false)
    private Date date;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "status", unique = false)
    private String status;

    public ProjectActivityEntity() {
    }

    public ProjectActivityEntity(ProjectEntity project, String name) {
        this.project = project;
        this.name = name;
    }

    public ProjectActivityEntity(ProjectEntity project, String name, Date date) {
        this.project = project;
        this.name = name;
        this.date = date;
    }

    public ProjectActivityEntity(ProjectEntity project, String name, Date date, String status) {
        this.project = project;
        this.name = name;
        this.date = date;
        this.status = status;
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        if (!(o instanceof ProjectActivityEntity)) return false;

        ProjectActivityEntity that = (ProjectActivityEntity) o;

        if (activityId != that.activityId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (activityId ^ (activityId >>> 32));
    }
}
