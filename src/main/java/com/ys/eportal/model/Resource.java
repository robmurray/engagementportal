package com.ys.eportal.model;

/**
 * Created by rob on 4/25/15.
 */
public class Resource {
    private long resourceId;
    private String firstName;
    private String lastName;

    public Resource() {
    }

    public Resource(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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