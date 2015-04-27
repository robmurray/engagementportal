package com.ys.eportal.infra.domain;

import javax.persistence.*;

/**
 * Created by rob on 4/25/15.
 */
@Entity
@Table(name = "ep_ProjectResource")
public class ProjectResourceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "projectResourceId")
    private long projectResourceId;

    @ManyToOne
    @Basic(fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId")
    private ProjectEntity project;

    @ManyToOne
    @Basic(fetch = FetchType.EAGER)
    @JoinColumn(name = "resourceId")
    private ResourceEntity resource;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "role")
    private String role;

    public ProjectResourceEntity() {
    }

    public ProjectResourceEntity(ProjectEntity project, ResourceEntity resource, String role) {
        this.project = project;
        this.resource = resource;
        this.role = role;
    }

    public long getProjectResourceId() {
        return projectResourceId;
    }

    public void setProjectResourceId(long projectResourceId) {
        this.projectResourceId = projectResourceId;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public ResourceEntity getResource() {
        return resource;
    }

    public void setResource(ResourceEntity resource) {
        this.resource = resource;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectResourceEntity)) return false;

        ProjectResourceEntity that = (ProjectResourceEntity) o;

        if (projectResourceId != that.projectResourceId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (projectResourceId ^ (projectResourceId >>> 32));
    }
}
