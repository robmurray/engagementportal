package com.ys.eportal.controller;

import com.ys.eportal.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class DashboardController {

    @Autowired
    private PortalService portalService;

    @RequestMapping(value="/dashboard", method= RequestMethod.GET)
    public String customerForm(Model model) {
        model.addAttribute("pageName", "Dashboard");

        return "dashboard";
    }

    @RequestMapping(value="/blank", method= RequestMethod.GET)
    public String blankPage(Model model) {
        model.addAttribute("pageName", "Dashboard");

        return "blank";
    }

}
