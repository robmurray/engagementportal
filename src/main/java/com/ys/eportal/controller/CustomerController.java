package com.ys.eportal.controller;



import com.ys.eportal.infra.domain.CustomerEntity;
import com.ys.eportal.mapper.CustomerMapper;
import com.ys.eportal.model.Customer;
import com.ys.eportal.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    private CustomerMapper customerMapper;

    @RequestMapping(value="/customernew", method= RequestMethod.GET)
    public String customerForm(Model model) {

        Customer customer = new Customer();
        addPageAttributes(model,"Create Customer", "Create new Customer");
        model.addAttribute("customer", customer);
        return "customernew";
    }

    @RequestMapping(value="/customernew", method=RequestMethod.POST)
    public String customerSubmit(@ModelAttribute Customer customer, Model model) {

        CustomerEntity c = this.customerMapper.convert(customer);
        this.portalService.saveCustomer(c);


        model.addAttribute("subTitle", "Create a new customer");
        model.addAttribute("pageName", "Save Customer");
        addPageAttributes(model,"Save Customer", "Save new Customer");
        model.addAttribute("customer", customer);
        return "customeredit?customerId="+customer.getCustomerId();
    }



/*
    public ModelAndView editProfileContact(@RequestParam(value="locale", required=false) String localeAsString,
                                           @RequestParam(value="type", required=false) String userType,
                                           @ModelAttribute("contact") UpdateContactPageModel pageModel,
                                           BindingResult result,
                                           HttpServletRequest request) {

*/
    @RequestMapping(value="/customeredit", method= RequestMethod.GET)
    public String customerEditForm(@RequestParam(value="customerId", required=true) Integer customerId,
                                   @RequestParam(value="msgtype", required=false) String messageType, Model model) {
        CustomerEntity customer = this.portalService.findCustomerByID(customerId);

        Customer c = this.customerMapper.convert(customer);

        addPageAttributes(model, "Edit Customer", "Edit the Customer ");
        model.addAttribute("customer", c);
        return "customerEdit";
    }

    @RequestMapping(value="/customeredit", method=RequestMethod.POST)
    public String customerEditSubmit(@ModelAttribute Customer customer, Model model) {

        CustomerEntity c = this.customerMapper.convert(customer);
        portalService.saveCustomer(c);
        customer = this.customerMapper.convert(c);

        model.addAttribute("customer", customer);
        addPageAttributes(model, "Save Customer", "Save Customer update");
        return "customerEdit";
    }

    protected void addPageAttributes(Model model, String pageName, String subTitle){
        model.addAttribute("subTitle", subTitle);
        model.addAttribute("pageName", pageName);
    }
}
