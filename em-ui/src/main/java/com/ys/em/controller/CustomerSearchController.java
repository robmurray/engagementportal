package com.ys.em.controller;

import com.ys.em.infra.domain.CustomerEntity;
import com.ys.em.mapper.CustomerMapper;
import com.ys.em.mapper.CustomerSearchMapper;
import com.ys.em.model.Customer;
import com.ys.em.model.CustomerSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class CustomerSearchController  extends EportalBaseController {


    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerSearchMapper customerSearchMapper;


    @RequestMapping(value="/customerSearch", method= RequestMethod.GET)
    public String customerForm(Model model) {

        model.addAttribute("pageName", "Customer Search");
        model.addAttribute("customersearch", new CustomerSearch());
        model.addAttribute("pageGroup", "customer");
        model.addAttribute("pageId", "searchCustomer");
        return "customerSearch";
    }

    @RequestMapping(value="/customerSearch", method=RequestMethod.POST)
    public String customerSubmit(@ModelAttribute CustomerSearch search, Model model) {
        model.addAttribute("pageName", "Customer Search");
        model.addAttribute("customersearch", search);

        Iterable<CustomerEntity> wrkList = this.portalService.find(this.customerSearchMapper.convert(search));


        Iterable<Customer> returnList = this.customerMapper.convert(wrkList);

        if(returnList !=null && !returnList.iterator().hasNext()){
            returnList =null;
        }
        model.addAttribute("customers", returnList);
        model.addAttribute("pageGroup", "customer");
        model.addAttribute("pageId", "searchCustomer");

        return "customerSearchResults";
    }

}
