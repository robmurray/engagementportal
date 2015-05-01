package com.ys.eportal.controller;


import com.ys.eportal.infra.domain.Constants;
import com.ys.eportal.infra.domain.ProjectActivityEntity;
import com.ys.eportal.mapper.CustomerMapper;
import com.ys.eportal.model.Activity;
import com.ys.eportal.service.PortalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class ActivityController extends ControllerBase {
    private static Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private PortalService portalService;

    @Autowired
    private CustomerMapper customerMapper;

    @RequestMapping(value = "/activity", method = RequestMethod.GET)
    public String activityViewForm(@RequestParam(value = "activityId", required = true) long activityId,
                                   @RequestParam(value = "returnURL", required = false) String returnURL,
                                   @RequestParam(value = "anchor", required = false) String anchor,
                                   @RequestParam(value = "msgtype", required = false) String messageType, Model model) {

        ProjectActivityEntity pae = this.portalService.findProjectActivityById(activityId);
        Activity a = null;
        if (pae != null) {
            a= this.loadActivity(pae);

        }
        addNav(model, returnURL,anchor);
        addPageAttributes(model, "Milestone", "Edit Milestone ");
        model.addAttribute("activity", a);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "activity";
    }

    @RequestMapping(value = "/activity", method = RequestMethod.POST)
    public String activitySubmit(@ModelAttribute Activity activity, Model model) {


        ProjectActivityEntity pae = this.portalService.findProjectActivityById(activity.getActivityId());
        // @TODO push to mapping layer
        if (pae != null) {


            pae.setDate(activity.getActivityDate());
            pae.setStatus(activity.getStatus());
            this.portalService.save(pae);

            // repull
            pae = this.portalService.findProjectActivityById(activity.getActivityId());
            activity= this.loadActivity(pae);

        }

        this.setSuccessAlertMessage(model, "Milestone updated");


        return activityViewForm(activity.getActivityId(), "", "","", model);


    }

    protected Activity loadActivity(ProjectActivityEntity pae){

        Activity a = null;
        if (pae != null) {
            a = new Activity();
            a.setActivityId(pae.getActivityId());
            a.setStatus(pae.getStatus());
            a.setActivityDate(pae.getDate());
            a.setName(pae.getName());
            a.setCreateDate(pae.getCreateDate());
            a.setModifiedDate(pae.getModifiedDate());

        }
        return a;
    }
    protected void addPageAttributes(Model model, String pageName, String subTitle) {
        model.addAttribute("subTitle", subTitle);
        model.addAttribute("pageName", pageName);
    }
}
