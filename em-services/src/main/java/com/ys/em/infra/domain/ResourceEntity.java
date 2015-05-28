package com.ys.em.infra.domain;

import com.ys.common.infra.domain.AbstractDomainBase;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by rob on 4/25/15.
 */
@Entity
@Table(name = "ep_Resource")
public class ResourceEntity extends AbstractDomainBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "resourceId")
    private long resourceId;


    @Basic(fetch = FetchType.EAGER)
    @Column(name = "firstName", unique = false)
    private String firstName;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "lastName", unique = false)
    private String lastName;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "type", unique = false)
    private String type;

    //@OneToMany(mappedBy = "resource ")
    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProjectResourceEntity> projectResources;

    public ResourceEntity() {
    }

    public ResourceEntity(String firstName, String lastName, String type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
    }

    public long getResourceId() {
        return resourceId;
    }

    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<ProjectResourceEntity> getProjectResources() {
        return projectResources;
    }

    public void setProjectResources(Set<ProjectResourceEntity> projectResources) {
        this.projectResources = projectResources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResourceEntity)) return false;

        ResourceEntity resource = (ResourceEntity) o;

        if (resourceId != resource.resourceId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (resourceId ^ (resourceId >>> 32));
    }
}
