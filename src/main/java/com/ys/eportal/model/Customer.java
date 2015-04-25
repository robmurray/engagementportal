package com.ys.eportal.model;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by rob on 4/4/15.
 */
public class Customer extends AbstractModelBase{

    private long customerId;
    @Size(min=2, max=30)
    private String name;
    private String notes;
    private List<Project> projects;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;

        if (customerId != customer.customerId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (customerId ^ (customerId >>> 32));
    }
}
