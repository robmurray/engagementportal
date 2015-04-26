package com.ys.eportal.infra.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rob on 4/8/15.
 */
@Entity
@Table(name = "ep_Customer")
public class CustomerEntity extends AbstractDomainBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customerId")
    private long customerId;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "name", unique = true)
    private String name;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "notes")
    private String notes;


/*
moved relation to salesorder
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProjectEntity> projects = new HashSet<ProjectEntity>();
*/
    private String contact;

    public CustomerEntity() {
    }

    public CustomerEntity(int customerId) {
        this.customerId = customerId;
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


    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerEntity)) return false;

        CustomerEntity that = (CustomerEntity) o;

        if (customerId != that.customerId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (customerId ^ (customerId >>> 32));
    }
}
