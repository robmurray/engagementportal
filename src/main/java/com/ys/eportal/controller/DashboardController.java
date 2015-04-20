package com.ys.eportal.controller;

import com.ys.eportal.model.Dashboard;
import com.ys.eportal.model.ProjectStats;
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

    @RequestMapping("/")
    public String stdRedirect(){
        return "redirect:/index";
    }

    @RequestMapping(value="/index", method= RequestMethod.GET)
    public String customerForm(Model model) {
        model.addAttribute("pageName", "Dashboard");
        Dashboard d = new Dashboard();


        ProjectStats ps = portalService.retrieveProjectStatus();

        model.addAttribute("dashboard",d );
        model.addAttribute("stats",ps );

        model.addAttribute("pageGroup", "dashboard");
        model.addAttribute("pageId", "dashboard");

        return "index";
    }



}
