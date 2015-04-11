package com.ys.eportal.service;

import com.ys.eportal.infra.domain.*;
import com.ys.eportal.infra.repository.CustomerRepository;
import com.ys.eportal.infra.repository.SalesOrderRepository;
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

    @Autowired
    private SalesOrderRepository salesOrderRepository;

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


    public CustomerEntity saveCustomer(CustomerEntity customer){
        return this.customerRepository.save(customer);
    }


    public void saveProject(SalesOrderEntity salesOrderEntity) {
        this.salesOrderRepository.save(salesOrderEntity);
    }

    public SalesOrderEntity findSalesOrderEntityById(int salesOrderId){

        return this.salesOrderRepository.findOne(salesOrderId);
    }
    public List<SalesOrderEntity> findAllSalesOrders(){
        return (List)this.salesOrderRepository.findAll();
    }

    public Iterable<SalesOrderEntity> find(ProjectSearchSupport search){
        List<SalesOrderEntity> results = null;

        if(search.getSalesOrderNumber()>0){
            SalesOrderEntity soe =this.salesOrderRepository.findOne(search.getSalesOrderNumber());
            results = new ArrayList<SalesOrderEntity>();
            results.add(soe);
        }else if(search.getCustomerName()!=null&& !search.getCustomerName().trim().equals("")){
            //results = this.findCustomerByName(search.getName());
            results = this.findAllSalesOrders();
        }else{
            results = this.findAllSalesOrders();
        }

        return results;
    }
}