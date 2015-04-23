package com.ys.eportal.service;

import com.ys.eportal.infra.domain.*;
import com.ys.eportal.infra.repository.*;
import com.ys.eportal.infra.domain.Constants;
import com.ys.eportal.model.ProjectStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by rob on 4/4/15.
 */

@Service
@Transactional
public class PortalService extends ServicesBase{

    private static Logger logger = LoggerFactory.getLogger(PortalService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SalesOrderRepository salesOrderRepository;



    public ProjectStats retrieveProjectStatus() {

        List<Object[]> list = this.salesOrderRepository.retrieveOrderStats();

        ProjectStats proj = new ProjectStats();
        // @TODO improve the ugliness
        if (list != null) {

            for (Object sos[] : list) {
                try {

                    // need to deal with the nordefinedstatus
                    if (sos[0] == null || sos[0].equals(Constants.SalesOrders.STATUS_NOTDEFINED)) {
                        proj.setNumNotdefinedStatus(((BigInteger) sos[1]).intValue());
                    } else if (sos[0].equals(Constants.SalesOrders.STATUS_BOOKED)) {
                        proj.setNumBookedStatus(((BigInteger) sos[1]).intValue());
                    } else if (sos[0].equals(Constants.SalesOrders.STATUS_COMPLETE)) {
                        proj.setNumCompleteStatus(((BigInteger) sos[1]).intValue());
                    } else if (sos[0].equals(Constants.SalesOrders.STATUS_INPROCESS)) {
                        proj.setNumInprocessStatus(((BigInteger) sos[1]).intValue());
                    } else if (sos[0].equals(Constants.SalesOrders.STATUS_POSTSUPPORT)) {
                        proj.setNumPostSupportStatus(((BigInteger) sos[1]).intValue());
                    } else if (sos[0].equals(Constants.SalesOrders.STATUS_PROPOSED)) {
                        proj.setNumProposedStatus(((BigInteger) sos[1]).intValue());
                    } else if (sos[0].equals(Constants.SalesOrders.STATUS_RANDSUPPORT)) {
                        proj.setNumRandSupportStatus(((BigInteger) sos[1]).intValue());
                    } else if (sos[0].equals(Constants.SalesOrders.STATUS_SCHEDULED)) {
                        proj.setNumScheduledStatus(((BigInteger) sos[1]).intValue());
                    }
                } catch (ClassCastException e) {
                    // ignore. rewrite
                }
            }

        }

        return proj;
    }

    public Iterable<CustomerEntity> find(CustomerSearchSupport search) {
        List<CustomerEntity> results = null;

        if (search.getCustomerId() > 0) {
            CustomerEntity c = this.findCustomerByID(search.getCustomerId());
            if(c!=null) {
                results = new ArrayList<CustomerEntity>();
                results.add(c);
            }
        } else if (search.getName() != null && !search.getName().trim().equals("")) {
            results = this.searchByCustomerName(search.getName());
        } else {
            results = this.findAllCustomers();
        }

        return results;
    }

    public CustomerEntity findCustomerByID(int customerId) {

        return this.customerRepository.findOne(customerId);
    }


    public CustomerEntity findCustomerByName(String name) {
        CustomerEntity customer = null;
        List<CustomerEntity> list = this.customerRepository.findByName(name);
        if (list != null && list.size() > 0) {
            customer = list.get(0);
        }
        return customer;
    }

    public List<CustomerEntity> searchByCustomerName(String name) {

        return this.customerRepository.findByNameLike(this.processWildCards(name));

    }


    public List<CustomerEntity> findAllCustomers() {
        return (List) this.customerRepository.findAll();
    }


    public CustomerEntity saveCustomer(CustomerEntity customer) {
        return this.customerRepository.save(customer);
    }


    public void saveProject(SalesOrderEntity salesOrderEntity) {
        this.salesOrderRepository.save(salesOrderEntity);
    }

    public SalesOrderEntity findSalesOrderEntityById(int salesOrderId) {
        return this.salesOrderRepository.findOne(salesOrderId);

    }

    public SalesOrderEntity findSalesOrderEntityByNumber(String salesOrderNumber) {
        List<SalesOrderEntity> list = this.salesOrderRepository.findBySalesOrderNumber(salesOrderNumber);
        SalesOrderEntity soe = null;
        if (list != null && list.size() > 0) {
            soe = list.get(0);
        }
        return soe;
    }


    public List<SalesOrderEntity> findByImportControlId(long importControlId) {
        List<SalesOrderEntity> list = this.salesOrderRepository.findByImportControlId(importControlId);

        return list;
    }

    public Set<SalesOrderEntity> findBySalesOrderCustomerIdlId(int customerId) {
        return this.salesOrderRepository.findByCustomerCustomerId(customerId);

    }

    public List<SalesOrderEntity> findByStatus(String status) {
        List<SalesOrderEntity> list = this.salesOrderRepository.findByStatus(status);

        return list;
    }

    public List<SalesOrderEntity> findAllSalesOrders() {
        return (List) this.salesOrderRepository.findAll();
    }

    public Iterable<SalesOrderEntity> find(ProjectSearchSupport search) {
        List<SalesOrderEntity> results = null;

        if (search.getSalesOrderNumber() != null && !search.getSalesOrderNumber().trim().equals("")) {
            SalesOrderEntity soe = this.findSalesOrderEntityByNumber(search.getSalesOrderNumber());
            if (soe != null) {
                results = new ArrayList<SalesOrderEntity>();
                results.add(soe);
            }
        } else if (search.getCustomerName() != null && !search.getCustomerName().trim().equals("")) {
            results = this.salesOrderRepository.findByCustomerNameLike(this.processWildCards(search.getCustomerName()));
            //results = this.findAllSalesOrders();
        } else if (search.getImportControlId() > 0) {
            results = this.findByImportControlId(search.getImportControlId());

        } else if (search.getStatus() != null && !search.getStatus().trim().equals("")) {
            results = this.findByStatus(search.getStatus());
        } else {
            results = this.findAllSalesOrders();
        }

        return results;
    }


}