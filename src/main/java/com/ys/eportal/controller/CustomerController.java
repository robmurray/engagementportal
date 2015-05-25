package com.ys.eportal.controller;


import com.ys.core.infra.domain.ep.CustomerEntity;
import com.ys.eportal.mapper.CustomerMapper;
import com.ys.eportal.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class CustomerController  extends EportalBaseController {
    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerMapper customerMapper;


    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String customerEditForm(@RequestParam(value = "customerId", required = true) Long customerId,
                                   @RequestParam(value = "returnURL", required = false) String returnURL,
                                   @RequestParam(value = "msgtype", required = false) String messageType, Model model) {

        CustomerEntity customer = this.portalService.findCustomerByID(customerId);


        Customer c = this.customerMapper.convert(customer);

        // pulls sos
        // @TODO update mappers
        //Iterable<SalesOrderEntity> sol = portalService.findBySalesOrderCustomerIdlId(c.getCustomerId());
       // Iterable<Project> projList = null;
        //if(sol!=null && sol.iterator().hasNext()) {
            //projList = this.projectMapper.convert(sol);
        //}
        addNav(model,returnURL);
        addPageAttributes(model, "Customer", "Edit the Customer ");
        model.addAttribute("customer", c);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "customer";
    }


    protected void addPageAttributes(Model model, String pageName, String subTitle) {
        model.addAttribute("subTitle", subTitle);
        model.addAttribute("pageName", pageName);
    }
}
