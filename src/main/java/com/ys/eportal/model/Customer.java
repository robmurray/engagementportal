package com.ys.eportal.model;

/**
 * Created by rob on 4/4/15.
 */
public class Customer {

    private long customerId;
    private String firstName;
    private String lastName;
    private double creditsAvailable;
    private String contact;

    public Customer() {

    }

    /**
     * for test purpose only
     * @TODO remove
     */
    public Customer(long customerId, String firstName, String lastName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public double getCreditsAvailable() {
        return creditsAvailable;
    }

    public void setCreditsAvailable(double creditsAvailable) {
        this.creditsAvailable = creditsAvailable;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
