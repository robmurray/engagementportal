package com.ys.um.infra.domain;

import com.ys.common.infra.domain.AbstractDomainBase;

import javax.persistence.*;

/**
 * Created by rob on 4/8/15.
 */
@Entity
@Table(name = "sec_User")
public class UserEntity extends AbstractDomainBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private long userId;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "firstName", unique = false)
    private String firstName;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "lastName", unique = false)
    private String lastName;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "notes")
    private String notes;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "email",unique = true )
    private String email;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "password")
    private String password;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "enabled")
    private boolean enabled;

    @OneToOne (cascade=CascadeType.ALL)
    @JoinColumn(name="roleId", unique= false, nullable=true, insertable=true, updatable=true)
    private RoleEntity role;

    public UserEntity() {
    }

    public UserEntity(String firstName, String lastName, String notes, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.notes = notes;
        this.email = email;
    }

    public UserEntity(String firstName, String lastName, String notes, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.notes = notes;
        this.email = email;
        this.password = password;
    }

    public UserEntity(String firstName, String lastName, String notes, String email, String password, boolean enabled) {
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

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (userId ^ (userId >>> 32));
    }
}
