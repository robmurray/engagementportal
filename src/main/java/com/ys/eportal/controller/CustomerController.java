package com.ys.eportal.controller;


import com.ys.eportal.infra.domain.CustomerEntity;
import com.ys.eportal.infra.domain.SalesOrderEntity;
import com.ys.eportal.mapper.CustomerMapper;
import com.ys.eportal.model.Customer;
import com.ys.eportal.model.Project;
import com.ys.eportal.service.PortalService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class CustomerController  extends ControllerBase{
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
