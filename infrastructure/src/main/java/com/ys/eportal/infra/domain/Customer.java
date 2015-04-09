package com.ys.eportal.infra.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rob on 4/8/15.
 */
@Entity
@Table(name = "ep_Customer")
public class Customer extends AbstractDomainBase {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
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
    private Set<SalesOrder> salesOrders = new HashSet<SalesOrder>();

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

    public Set<SalesOrder> getSalesOrders() {
        return salesOrders;
    }

    public void setSalesOrders(Set<SalesOrder> salesOrders) {
        this.salesOrders = salesOrders;
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
        return customerId;
    }
}
