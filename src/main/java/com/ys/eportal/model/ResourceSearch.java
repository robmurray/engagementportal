package com.ys.eportal.model;

import com.ys.eportal.infra.domain.Constants;

import java.util.List;

/**
 * Created by rob on 4/5/15.
 */
public class ResourceSearch {
    private String firstName;
    private String lastName;
    private String type;
    private List<String> typeValues = Constants.Resource.typeValues;

    public ResourceSearch() {
    }

    public ResourceSearch(String firstName, String lastName, String type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
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

    public List<String> getTypeValues() {
        return typeValues;
    }

    public void setTypeValues(List<String> typeValues) {
        this.typeValues = typeValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResourceSearch)) return false;

        ResourceSearch that = (ResourceSearch) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (typeValues != null ? !typeValues.equals(that.typeValues) : that.typeValues != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
