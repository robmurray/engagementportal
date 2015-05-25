package com.ys.eportal.controller;

import org.springframework.stereotype.Controller;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class DashboardController  extends EportalBaseController {
/*
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

*/

}
