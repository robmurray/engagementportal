package com.ys.eportal.controller;

import com.ys.eportal.model.Customer;
import com.ys.eportal.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class CustomerController {

    @Autowired
    private PortalService portalService;

    @RequestMapping(value="/customernew", method= RequestMethod.GET)
    public String customerForm(Model model) {

        Customer customer = new Customer();
        addPageAttributes(model,"Create Customer", "Create new Customer");
        model.addAttribute("customer", customer);
        return "customernew";
    }

    @RequestMapping(value="/customernew", method=RequestMethod.POST)
    public String customerSubmit(@ModelAttribute Customer customer, Model model) {

        //customer = portalService.findCustomerByID(1);
        // for now
        customer.setCustomerId(1);
        model.addAttribute("subTitle", "Create a new customer");
        model.addAttribute("pageName", "Save Customer");
        addPageAttributes(model,"Save Customer", "Save new Customer");
        model.addAttribute("customer", customer);
        return "customerNewResult";
    }

    @RequestMapping(value="/customeredit", method= RequestMethod.GET)
    public String customerEditForm(Model model) {

        long customerId = 1;

        Customer customer = this.portalService.findCustomerByID(customerId);

        addPageAttributes(model, "Edit Customer", "Edit the Customer ");
        model.addAttribute("customer", customer);
        return "customerEdit";
    }

    @RequestMapping(value="/customeredit", method=RequestMethod.POST)
    public String customerEditSubmit(@ModelAttribute Customer customer, Model model) {

        //customer = portalService.findCustomerByID(1);
        // for now
        customer.setCustomerId(1);
        model.addAttribute("customer", customer);
        addPageAttributes(model, "Save Customer", "Save Customer update");
        return "customerEditResult";
    }

    protected void addPageAttributes(Model model, String pageName, String subTitle){
        model.addAttribute("subTitle", subTitle);
        model.addAttribute("pageName", pageName);
    }
}
