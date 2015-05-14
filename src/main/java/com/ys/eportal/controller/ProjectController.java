package com.ys.eportal.controller;

import com.ys.eportal.infra.domain.*;
import com.ys.eportal.model.*;
import com.ys.eportal.service.PortalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class ProjectController extends ControllerBase {
    private static Logger logger = LoggerFactory.getLogger(ProjectController.class);


    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public String projectView(@RequestParam(value = "projectId", required = true) long projectId,
                              @RequestParam(value = "returnURL", required = false) String returnURL,
                              @RequestParam(value = "msgtype", required = false) String messageType, Model model) {

        ProjectEntity pe = this.portalService.findProjectByProjectId(projectId);

        Project project = this.loadProject(pe);

            // @TODO check aauthorization
            project.setReadonly(false);

        addNav(model,returnURL);
        model.addAttribute("pageName", "Project");
        model.addAttribute("project", project);
        model.addAttribute("returnURL", "project?projectId="+projectId);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "project";
    }




   @RequestMapping(value="/projectInfo", method=RequestMethod.POST)
    public String projectEditSubmit(@ModelAttribute Project project, Model model) {

        ProjectEntity pe = this.retrieveProject(project);

       // @TODO push to mapping layer
        if(pe!=null) {
            pe.setHealth(project.getHealthStatus());
            pe.setLocation(project.getLocation());
            pe.setService(project.getService());
            pe.setStatus(project.getStatus());
            pe.setWaitTime(project.getWaitTime());

            this.portalService.save(pe);
        }
        this.setSuccessAlertMessage(model,"project updated");
        model.addAttribute("pageName", "Save Project");
        model.addAttribute("project", project);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "project";
    }


    @RequestMapping(value="/projectNote", method=RequestMethod.POST)
    public String projectNoteSubmit(@RequestParam(value="id",required=true) long id,@RequestParam(value="newnote",required=false) String newnote, Model model) {


        ProjectEntity pe = this.portalService.findProjectByProjectId(id);

        if(pe !=null) {
            pe.addNotes(new ProjectNotesEntity(pe, newnote));
            //ProjectNotesEntity pne = new ProjectNotesEntity(pe, newnote);
            this.portalService.save(pe);
            //pe = this.portalService.findProjectByProjectId(id);
        }
        Project project = this.loadProject(pe);

        //this.setSuccessAlertMessage(model,"project updated");
        model.addAttribute("pageName", "Save Project");
        model.addAttribute("project", project);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        model.addAttribute("anchor", "noteForm");
        //return new ModelAndView(new RedirectView("project#notesForm", true));


        return projectView(id,"","", model);

            //    return "redirect:project#notesForm?projectId="+id;
    }





}
