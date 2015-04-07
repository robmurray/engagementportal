package com.ys.eportal.model;

/**
 * Created by rob on 4/5/15.
 */
public class CustomerSearch {
    private long customerId;
    private String firstName;
    private String lastName;

    public CustomerSearch() {
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
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

}
