package com.ys.em.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class RootController extends EportalBaseController {


    @RequestMapping("/")
    public String stdRedirect(){
        return "redirect:/projectSearch";
    }

    @RequestMapping(value="/index", method= RequestMethod.GET)
    public String stdRedirectIndex(){
        return "redirect:/projectSearch";
    }


}
