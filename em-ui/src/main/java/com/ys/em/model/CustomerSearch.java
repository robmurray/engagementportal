package com.ys.em.model;

/**
 * Created by rob on 4/5/15.
 */
public class CustomerSearch {
    private long customerId;
    private String name;

    public CustomerSearch() {
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
}
