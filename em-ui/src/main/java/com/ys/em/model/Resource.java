package com.ys.em.model;

import com.ys.em.infra.domain.Constants;

import java.util.List;

/**
 * Created by rob on 4/25/15.
 */
public class Resource {

    private long resourceId;
    private long projectResourceId;
    private String firstName;
    private String lastName;
    private String type;
    private List<String> typeValues = Constants.Resource.typeValues;
    private String resourceRole;
    private List<String> resourceRoleValues = Constants.ResourceRole.typeValues;

    public Resource() {
    }

    public Resource(String firstName, String lastName, String type, String resourceRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.resourceRole = resourceRole;
    }

    public Resource(long resourceId, long projectResourceId, String firstName, String lastName, String type, String resourceRole) {
        this.resourceId = resourceId;
        this.projectResourceId = projectResourceId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.resourceRole = resourceRole;
    }

    public List<String> getTypeValues() {
        return typeValues;
    }

    public long getProjectResourceId() {
        return projectResourceId;
    }

    public void setProjectResourceId(long projectResourceId) {
        this.projectResourceId = projectResourceId;
    }

    public void setTypeValues(List<String> typeValues) {
        this.typeValues = typeValues;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public String getResourceRole() {
        return resourceRole;
    }

    public void setResourceRole(String resourceRole) {
        this.resourceRole = resourceRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resource)) return false;

        Resource resource = (Resource) o;

        if (resourceId != resource.resourceId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (resourceId ^ (resourceId >>> 32));
    }
}
