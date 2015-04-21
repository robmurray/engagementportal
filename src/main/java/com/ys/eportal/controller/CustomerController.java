package com.ys.eportal.controller;


import com.ys.eportal.infra.domain.CustomerEntity;
import com.ys.eportal.mapper.CustomerMapper;
import com.ys.eportal.model.Customer;
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

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class CustomerController  extends ControllerBase{
    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private PortalService portalService;

    @Autowired
    private CustomerMapper customerMapper;

    @RequestMapping(value = "/customerNew", method = RequestMethod.GET)
    public String customerForm(Model model) {

        Customer customer = new Customer();
        addPageAttributes(model, "Create Customer", "Create new Customer");
        model.addAttribute("customer", customer);
        model.addAttribute("pageGroup", "customer");
        model.addAttribute("pageId", "createCustomer");
        return "customerNew";
    }

    @RequestMapping(value = "/customerNew", method = RequestMethod.POST)
    public String customerSubmit(@Valid Customer customer, BindingResult bindingResult, Model model) {

        // look for dup
        if (customer != null && StringUtils.isNotEmpty(customer.getName())) {

            CustomerEntity wrkCustomer = this.portalService.findCustomerByName(customer.getName());
            if (wrkCustomer != null){
                logger.debug("duplicate name error");
                bindingResult.rejectValue("name","duplicate_name", "duplicate name");
            }

        }


        if (bindingResult.hasErrors()) {
            logger.debug("validation errors");
            return "customerNew";
        }

        CustomerEntity c = this.customerMapper.convert(customer);
        c = this.portalService.saveCustomer(c);
        customer = this.customerMapper.convert(c);

        model.addAttribute("subTitle", "Create a new customer");
        model.addAttribute("pageName", "Save Customer");
        addPageAttributes(model, "Save Customer", "Save new Customer");
        model.addAttribute("customer", customer);

        model.addAttribute("pageGroup", "customer");
        model.addAttribute("pageId", "createCustomer");
        this.setSuccessAlertMessage(model,"customer created");
        return customerEditForm(customer.getCustomerId(), "", model); //"customerEdit?customerId="+customer.getCustomerId();
    }


    /*
        public ModelAndView editProfileContact(@RequestParam(value="locale", required=false) String localeAsString,
                                               @RequestParam(value="type", required=false) String userType,
                                               @ModelAttribute("contact") UpdateContactPageModel pageModel,
                                               BindingResult result,
                                               HttpServletRequest request) {

    */
    @RequestMapping(value = "/customerEdit", method = RequestMethod.GET)
    public String customerEditForm(@RequestParam(value = "customerId", required = true) Integer customerId,
                                   @RequestParam(value = "msgtype", required = false) String messageType, Model model) {
        CustomerEntity customer = this.portalService.findCustomerByID(customerId);

        Customer c = this.customerMapper.convert(customer);

        addPageAttributes(model, "Edit Customer", "Edit the Customer ");
        model.addAttribute("customer", c);
        model.addAttribute("projects", c.getProjects());
        model.addAttribute("pageGroup", "customer");
        model.addAttribute("pageId", "searchCustomer");
        return "customerEdit";
    }

    @RequestMapping(value = "/customerEdit", method = RequestMethod.POST)
    public String customerEditSubmit(@ModelAttribute Customer customer, Model model) {

        CustomerEntity c = this.customerMapper.convert(customer);


        try {
            portalService.saveCustomer(c);
            // @TODO anstract
        } catch (ConstraintViolationException e) {


        }
        customer = this.customerMapper.convert(c);
        this.setSuccessAlertMessage(model,"customer updated");

        model.addAttribute("customer", customer);
        model.addAttribute("projects", customer.getProjects());
        addPageAttributes(model, "Save Customer", "Save Customer update");
        model.addAttribute("pageGroup", "customer");
        model.addAttribute("pageId", "searchCustomer");
        model.addAttribute("projects", customer.getProjects());
        return "customerEdit";
    }

    protected void addPageAttributes(Model model, String pageName, String subTitle) {
        model.addAttribute("subTitle", subTitle);
        model.addAttribute("pageName", pageName);
    }
}
