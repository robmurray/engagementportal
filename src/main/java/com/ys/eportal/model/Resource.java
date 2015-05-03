package com.ys.eportal.model;

import com.ys.eportal.infra.domain.Constants;

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
    public Resource() {
    }

    public Resource(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Resource(String firstName, String lastName, String type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
    }

    public Resource(long resourceId, String firstName, String lastName, String type) {
        this.resourceId = resourceId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
    }

    public Resource(long resourceId, long projectResourceId, String firstName, String lastName, String type) {
        this.resourceId = resourceId;
        this.projectResourceId = projectResourceId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
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
