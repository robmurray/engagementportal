package com.ys.eportal.controller;


import com.ys.eportal.infra.domain.CustomerEntity;
import com.ys.eportal.infra.domain.ProjectActivityEntity;
import com.ys.eportal.mapper.CustomerMapper;
import com.ys.eportal.model.Activity;
import com.ys.eportal.model.Customer;
import com.ys.eportal.service.PortalService;
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
public class ActivityController extends ControllerBase{
    private static Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private PortalService portalService;

    @Autowired
    private CustomerMapper customerMapper;


    @RequestMapping(value = "/activity", method = RequestMethod.GET)
    public String customerEditForm(@RequestParam(value = "activityId", required = true) long activityId,
                                   @RequestParam(value = "returnURL", required = false) String returnURL,
                                   @RequestParam(value = "msgtype", required = false) String messageType, Model model) {

        ProjectActivityEntity pae = this.portalService.findProjectActivityById(activityId);
        Activity a =null;
        if(pae!=null) {
            a = new Activity();
            a.setActivityId(pae.getActivityId());
            a.setStatus(pae.getStatus());
            a.setDate(pae.getDate());
            a.setName(pae.getName());
            a.setCreateDate(pae.getCreateDate());
            a.setModifiedDate(pae.getModifiedDate());

        }
        addNav(model, returnURL);
        addPageAttributes(model, "Milestone", "Edit Milestone ");
        model.addAttribute("activity", a);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "activity";
    }


    protected void addPageAttributes(Model model, String pageName, String subTitle) {
        model.addAttribute("subTitle", subTitle);
        model.addAttribute("pageName", pageName);
    }
}
