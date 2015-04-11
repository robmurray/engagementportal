package com.ys.eportal.controller;

import com.ys.eportal.infra.domain.CustomerEntity;
import com.ys.eportal.mapper.CustomerMapper;
import com.ys.eportal.mapper.CustomerSearchMapper;
import com.ys.eportal.model.Customer;
import com.ys.eportal.model.CustomerSearch;
import com.ys.eportal.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Iterator;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class CustomerSearchController {

    @Autowired
    private PortalService portalService;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerSearchMapper customerSearchMapper;


    @RequestMapping(value="/customerSearch", method= RequestMethod.GET)
    public String customerForm(Model model) {

        model.addAttribute("pageName", "Customer Search");
        model.addAttribute("customersearch", new CustomerSearch());
        return "customerSearch";
    }

    @RequestMapping(value="/customerSearch", method=RequestMethod.POST)
    public String customerSubmit(@ModelAttribute CustomerSearch search, Model model) {
        model.addAttribute("pageName", "Customer Search");
        model.addAttribute("customersearch", search);

        Iterable<CustomerEntity> wrkList = this.portalService.find(this.customerSearchMapper.convert(search));


        Iterable<Customer> returnList = this.customerMapper.convert(wrkList);

        model.addAttribute("customers", returnList);

        return "customerSearchResults";
    }

}
