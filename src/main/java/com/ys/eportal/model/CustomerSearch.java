package com.ys.eportal.model;

/**
 * Created by rob on 4/5/15.
 */
public class CustomerSearch {
    private int customerId;
    private String name;

    public CustomerSearch() {
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
}
