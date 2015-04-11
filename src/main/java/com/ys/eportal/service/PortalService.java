package com.ys.eportal.service;

import com.ys.eportal.infra.domain.CustomerEntity;
import com.ys.eportal.infra.domain.CustomerSearchSupport;
import com.ys.eportal.infra.domain.SalesOrderEntity;
import com.ys.eportal.infra.repository.CustomerRepository;
import com.ys.eportal.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by rob on 4/4/15.
 */

@Service
public class PortalService {

    @Autowired
    private CustomerRepository customerRepository;

    public Iterable<CustomerEntity> find(CustomerSearchSupport search){
        List<CustomerEntity> results = null;

        if(search.getCustomerId()>0){
            CustomerEntity c = this.findCustomerByID(search.getCustomerId());
            results = new ArrayList<CustomerEntity>();
            results.add(c);
        }else if(search.getName()!=null&& !search.getName().trim().equals("")){
            results = this.findCustomerByName(search.getName());
        }else{
            results = this.findAllCustomers();
        }

        return results;
    }

    public CustomerEntity findCustomerByID(int customerId){

        return this.customerRepository.findOne(customerId);
    }


    public List<CustomerEntity> findCustomerByName(String name){
        return this.customerRepository.findByName(name);
    }


    public List<CustomerEntity> findAllCustomers(){
       return (List)this.customerRepository.findAll();
    }


    public void saveCustomer(CustomerEntity customer){
        this.customerRepository.save(customer);

    }

    public void saveProject(SalesOrderEntity salesOrderEntity) {

    }

/*
    public Project findProjectById(long projectId){
        return PortalService.loadTestProject(1);
    }

    public Set<Project>findAllProjects(){
        return PortalService.loadTestProjectList();
    }


    // @TODO remove test code
    private static Customer loadTestCustomer(long customerID, String firstName, String lastName){
        return new Customer(customerID,firstName,lastName);
    }
    // @TODO remove test code
    private static Set<Customer> loadTestCustomerList(){

        Set<Customer> set = new HashSet<Customer>();

        set.add(PortalService.loadTestCustomer(1,"John","Smith"));
        set.add(PortalService.loadTestCustomer(2,"Bill","Smith"));
        set.add(PortalService.loadTestCustomer(3,"Tammy","Smith"));
        set.add(PortalService.loadTestCustomer(4,"John","Paul"));
        set.add(PortalService.loadTestCustomer(5,"Terry","George"));
        set.add(PortalService.loadTestCustomer(6,"Sam","Franken"));
        set.add(PortalService.loadTestCustomer(7,"John","Murray"));
        set.add(PortalService.loadTestCustomer(8,"Bill","Murray"));
        set.add(PortalService.loadTestCustomer(9,"Susan","Donaldson"));
        set.add(PortalService.loadTestCustomer(10,"John","Smith"));
        return set;
    }

    // @TODO remove test code
    private static Project loadTestProject(long projectID){
        return new Project(projectID, projectID+"salesChannel1", 100+(int)projectID, projectID+"accountTeamA", 9000.00, new Date(), projectID+"modelTypeA", projectID+" a description Of the Service",100);
     }

    // @TODO remove test code
    private static Set<Project>loadTestProjectList(){
        Set<Project> set = new HashSet<Project>();
        set.add(PortalService.loadTestProject(1));
        set.add(PortalService.loadTestProject(2));
        set.add(PortalService.loadTestProject(3));
        set.add(PortalService.loadTestProject(4));
        set.add(PortalService.loadTestProject(5));
        set.add(PortalService.loadTestProject(6));
        set.add(PortalService.loadTestProject(7));
        set.add(PortalService.loadTestProject(8));
        set.add(PortalService.loadTestProject(9));
        set.add(PortalService.loadTestProject(10));
        set.add(PortalService.loadTestProject(11));
        set.add(PortalService.loadTestProject(12));
        return set;

    }

    */
}