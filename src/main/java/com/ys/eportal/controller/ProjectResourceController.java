package com.ys.eportal.controller;


import com.ys.core.infra.domain.ep.ProjectEntity;
import com.ys.core.infra.domain.ep.ProjectResourceEntity;
import com.ys.core.infra.domain.ep.ResourceEntity;
import com.ys.eportal.model.Project;
import com.ys.eportal.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class ProjectResourceController extends EportalBaseController {
    private static Logger logger = LoggerFactory.getLogger(ProjectResourceController.class);


    @RequestMapping(value = "/projectResourceAdd", method = RequestMethod.POST)
    public String resourceAddSubmitForm(@RequestParam(value = "resourceId", required = true) long resourceId, @RequestParam(value = "index", required = true) String index, @RequestParam(value = "projectId", required = true) long projectId, @RequestParam(value = "role", required = true) String role, Model model) {


        // long resourceId = resource.getResourceId();
        // find the resource
        ResourceEntity re = this.portalService.findResourceEntityById(resourceId);
        ProjectEntity pe = this.portalService.findProjectByProjectId(projectId);

        if (re != null && pe != null) {
            // ok we have a resource now make sure the resouece is not already attached to the project
            ProjectResourceEntity pre = new ProjectResourceEntity(pe, re, role);
            this.portalService.save(pre);
        }
        pe = this.portalService.findProjectByProjectId(projectId);
        Project project = this.loadProject(pe);


        model.addAttribute("project", project);
        this.setSuccessAlertMessage(model, "Resource updated");

        addNav(model, "projectSearch");
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        model.addAttribute("anchor", index);
        return "project";


    }


    @RequestMapping(value = "/projectResourceDelete", method = RequestMethod.GET)
    public String resourceDeleteSubmit(@RequestParam(value = "projectResourceId", required = true) long projectResourceId,
                                       @RequestParam(value = "returnURL", required = false) String returnURL,
                                       @RequestParam(value = "index", required = false) String index,
                                       Model model) {


        // pull original
        ProjectResourceEntity pre = this.portalService.findProjectResourceById(projectResourceId);
        long projectId = 0;


        if (pre != null) {
            projectId = pre.getProject().getProjectId();
            this.portalService.delete(pre);


        }

        // reload
        ProjectEntity pe = this.portalService.findProjectByProjectId(projectId);
        Project project = this.loadProject(pe);


        model.addAttribute("project", project);
        addNav(model, "projectSearch");
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        model.addAttribute("anchor", index);
        return "project";

    }

    protected Resource load(ResourceEntity pae) {
        Resource r = null;
        if (pae != null) {
            r = new Resource();
            r.setResourceId(pae.getResourceId());
            r.setFirstName(pae.getFirstName());
            r.setLastName(pae.getLastName());
            r.setResourceId(pae.getResourceId());
            r.setType(pae.getType());
        }
        return r;
    }

    protected void addPageAttributes(Model model, String pageName, String subTitle) {
        model.addAttribute("subTitle", subTitle);
        model.addAttribute("pageName", pageName);
    }
}
