package com.ys.eportal.model;

import java.util.List;

/**
 * Created by rob on 4/4/15.
 */
public class Customer extends AbstractModelBase{

    private int customerId;
    private String name;
    private int credits;
    private String contact;
    private List<Project> projects;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
        return customerId;
    }
}
