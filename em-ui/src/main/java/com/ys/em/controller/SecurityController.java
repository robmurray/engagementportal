package com.ys.em.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class SecurityController  extends EportalBaseController {

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String loginForm(Model model) {

        return "login";
    }
}
