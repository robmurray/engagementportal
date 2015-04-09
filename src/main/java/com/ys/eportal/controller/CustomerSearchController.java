package com.ys.eportal.controller;

import com.ys.eportal.infra.domain.CustomerEntity;
import com.ys.eportal.mapper.CustomerMapper;
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

    @RequestMapping(value="/customersearch", method= RequestMethod.GET)
    public String customerForm(Model model) {

        model.addAttribute("pageName", "Customer Search");
        model.addAttribute("customersearch", new CustomerSearch());
        return "customerSearch";
    }

    @RequestMapping(value="/customersearch", method=RequestMethod.POST)
    public String customerSubmit(@ModelAttribute CustomerSearch search, Model model) {
        model.addAttribute("pageName", "Customer Search");
        model.addAttribute("customersearch", search);

        Iterable<CustomerEntity> list = portalService.findAllCustomers();

        Iterable<Customer> returnList = this.customerMapper.convert(list);

        model.addAttribute("customers", returnList);

        return "customerSearchResults";
    }

}
