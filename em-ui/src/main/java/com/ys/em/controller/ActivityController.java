package com.ys.em.controller;


import com.ys.em.infra.domain.CustomerEntity;
import com.ys.em.infra.domain.ProjectActivityEntity;
import com.ys.em.infra.domain.ProjectEntity;
import com.ys.em.mapper.CustomerMapper;
import com.ys.em.model.Activity;
import com.ys.em.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
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
public class ActivityController extends EportalBaseController {
    private static Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private CustomerMapper customerMapper;

    @RequestMapping(value = "/activity", method = RequestMethod.GET)
    public String activityViewForm(@RequestParam(value = "activityId", required = true) long activityId,
                                   @RequestParam(value = "returnURL", required = false) String returnURL,
                                   @RequestParam(value = "tabIndex", required = false) String tabIndex,
                                    Model model) {

        ProjectActivityEntity pae = this.portalService.findProjectActivityById(activityId);
        Activity a = null;
        if (pae != null) {
            a= this.loadActivity(pae);

        }
        //addNav(model, returnURL,anchor);
        //model.addAttribute("returnURL", "project?projectId="+pae.getProject().getProjectId());
        addNav(model, "project?projectId="+pae.getProject().getProjectId());
        addPageAttributes(model, "Milestone", "Edit Milestone ");
        model.addAttribute("activity", a);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        model.addAttribute("tabIndex", tabIndex);
        return "activity";
    }

    @RequestMapping(value = "/activity", method = RequestMethod.POST)
    public String activitySubmit(@ModelAttribute Activity activity,
                                 @RequestParam(value = "tabIndex", required = false) String tabIndex, Model model) {


        ProjectActivityEntity pae = this.portalService.findProjectActivityById(activity.getActivityId());
        Project p = null;
        ProjectEntity pe = null;
        // @TODO push to mapping layer
        if (pae != null) {


            pae.setDate(activity.getActivityDate());
            pae.setStatus(activity.getStatus());
            this.portalService.save(pae);

            // repull
            pae = this.portalService.findProjectActivityById(activity.getActivityId());
          //  activity= this.loadActivity(pae);

            pe = pae.getProject();
            p = loadProject(pe);
        }
        addNav(model, "projectSearch");
        //this.setSuccessAlertMessage(model, "Milestone updated");
        model.addAttribute("anchor", "projectactivitiesform");
        model.addAttribute("projectId",pae.getProject().getProjectId());


        // hack @TODO fix
        model.addAttribute("pageName", "Project");
        model.addAttribute("project",p );
        //model.addAttribute("returnURL", "project?projectId="+pae.getProject().getProjectId());
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        model.addAttribute("tabIndex",tabIndex);
        return "project";


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
