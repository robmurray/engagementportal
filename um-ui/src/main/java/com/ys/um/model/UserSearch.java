package com.ys.um.model;

import com.ys.ui.model.PageModelBase;

/**
 * Created by rob on 4/5/15.
 */
public class UserSearch extends PageModelBase {

    private long userId;
    private String email;
    private String firstName;
    private String lastName;

    public UserSearch() {
    }

    public UserSearch(long userId, String email, String firstName, String lastName) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

