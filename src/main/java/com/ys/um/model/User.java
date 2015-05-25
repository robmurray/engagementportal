package com.ys.um.model;

import com.ys.core.infra.domain.user.RoleEntity;
import com.ys.ui.model.PageModelBase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class User extends PageModelBase {

    private long userId;

    private String firstName;

    private String lastName;

    private String notes;

    private String email;

    private String password;

    private boolean enabled;

    private Role role;

    private Iterable<Role> roleValues = new ArrayList<Role>();

    public User() {
    }

    public User(String firstName, String lastName, String notes, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.notes = notes;
        this.email = email;
    }

    public User(String firstName, String lastName, String notes, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.notes = notes;
        this.email = email;
        this.password = password;
    }

    public User(String firstName, String lastName, String notes, String email, String password, boolean enabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.notes = notes;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Iterable<Role> getRoleValues() {
        return roleValues;
    }

    public void setRoleValues(Iterable<Role> rolesValues) {
        this.roleValues = rolesValues;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
