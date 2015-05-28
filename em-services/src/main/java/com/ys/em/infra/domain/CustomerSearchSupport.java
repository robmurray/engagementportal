package com.ys.em.infra.domain;

/**
 * Created by rob on 4/10/15.
 */
public class CustomerSearchSupport {

    private int customerId;
    private String name;


    public CustomerSearchSupport() {
    }

    public CustomerSearchSupport(int customerId) {
        this.customerId = customerId;
    }

    public CustomerSearchSupport(String name) {
        this.name = name;
    }

    public CustomerSearchSupport(int customerId, String name) {
        this.customerId = customerId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerSearchSupport)) return false;

        CustomerSearchSupport that = (CustomerSearchSupport) o;

        if (customerId != that.customerId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
