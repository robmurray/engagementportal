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
public class ReportsController extends ControllerBase{


    @RequestMapping(value="/qsTimeToFulfill", method= RequestMethod.GET)
    public String customerForm(Model model) {
        model.addAttribute("pageName", "Reports");
        Dashboard d = new Dashboard();


        //ProjectStats ps = portalService.retrieveProjectStatus();

        //model.addAttribute("dashboard",d );
        //model.addAttribute("stats",ps );

        model.addAttribute("pageGroup", "reports");
        model.addAttribute("pageId", "qsTimeToFulfill");

        return "qsTimeToFulfill";
    }



}
