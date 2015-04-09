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
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "customerId")
    private int customerId;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "name", unique = true)
    private String name;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "credits")
    private int credits;

    /*
        @TODO need to think about how cascade deletes are handled
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customerId")
    private Set<SalesOrderEntity> salesOrders = new HashSet<SalesOrderEntity>();

    private String contact;


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

    public Set<SalesOrderEntity> getSalesOrders() {
        return salesOrders;
    }

    public void setSalesOrders(Set<SalesOrderEntity> salesOrders) {
        this.salesOrders = salesOrders;
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

        CustomerEntity customer = (CustomerEntity) o;

        if (customerId != customer.customerId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return customerId;
    }
}
