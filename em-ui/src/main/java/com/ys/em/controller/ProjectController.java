package com.ys.em.controller;

import com.ys.em.infra.domain.ProjectEntity;
import com.ys.em.infra.domain.ProjectNotesEntity;
import com.ys.em.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ProjectController extends EportalBaseController {
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
            long projectId = pe.getProjectId();
            pe.setHealth(project.getHealthStatus());
            pe.setLocation(project.getLocation());
            pe.setService(project.getService());
            pe.setStatus(project.getStatus());
            pe.setWaitTime(project.getWaitTime());

            this.portalService.save(pe);
            // repull
            pe = this.portalService.findProjectByProjectId(projectId);
            project = this.loadProject(pe);
        }


        //this.setSuccessAlertMessage(model,"project updated");
        model.addAttribute("pageName", "Save Project");
        model.addAttribute("project", project);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "project";
    }


    @RequestMapping(value="/projectCreditAdd", method=RequestMethod.POST)
    public String projectCreditDELSubmit(@RequestParam(value="projectId",required=true) long projectId,@RequestParam(value="numOfCredits",required=true) long credits, @RequestParam(value="anchor",required=true) String anchor, Model model) {


        ProjectEntity pe = this.portalService.findProjectByProjectId(projectId);

        if(pe !=null) {

            long newBalance = pe.getCredits()+credits;
            pe.setCredits(newBalance);
            this.portalService.save(pe);
            //repull
            pe = this.portalService.findProjectByProjectId(projectId);
        }
        Project project = this.loadProject(pe);

        model.addAttribute("pageName", "Save Project");
        model.addAttribute("project", project);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        model.addAttribute("anchor", anchor);

        return "project";
    }

    @RequestMapping(value="/projectCreditSubtract", method=RequestMethod.POST)
    public String projectCreditADDSubmit(@RequestParam(value="projectId",required=true) long projectId,@RequestParam(value="numOfCredits",required=true) long credits, @RequestParam(value="anchor",required=true) String anchor, Model model) {


        ProjectEntity pe = this.portalService.findProjectByProjectId(projectId);

        if(pe !=null) {

            long newBalance = pe.getCredits()-credits;
            pe.setCredits(newBalance);
            this.portalService.save(pe);
            //repull
            pe = this.portalService.findProjectByProjectId(projectId);
        }
        Project project = this.loadProject(pe);

        model.addAttribute("pageName", "Save Project");
        model.addAttribute("project", project);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        model.addAttribute("anchor", anchor);

        return "project";
    }


    @RequestMapping(value="/projectNote", method=RequestMethod.POST)
    public String projectNoteSubmit(@RequestParam(value="id",required=true) long id,
                                    @RequestParam(value="newnote",required=false) String newnote,
                                    @RequestParam(value = "tabIndex", required = false) String tabIndex,Model model) {


        ProjectEntity pe = this.portalService.findProjectByProjectId(id);

        if(pe !=null) {
            pe.addNotes(new ProjectNotesEntity(pe, newnote));
            this.portalService.save(pe);
        }
        Project project = this.loadProject(pe);

        model.addAttribute("pageName", "Save Project");
        model.addAttribute("project", project);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        model.addAttribute("tabIndex", tabIndex);

        return "project";
    }


    @RequestMapping(value = "/projectNotesDelete", method = RequestMethod.GET)
    public String projectNoteDeleteSubmit(@RequestParam(value = "projectNotesId", required = true) long projectNoteId,
                                          @RequestParam(value = "tabIndex", required = false) String tabIndex,
                                          @RequestParam(value = "returnURL", required = false) String returnURL,
                                       Model model){


        // pull original
        ProjectNotesEntity pne = this.portalService.findProjectNoteById(projectNoteId);


        Project project = null;
        if (pne != null) {
            long projectId = pne.getProject().getProjectId();
            this.portalService.delete(pne);
            // repull
            ProjectEntity pe = this.portalService.findProjectByProjectId(projectId);
            project = this.loadProject(pe);
        }

        model.addAttribute("pageName", "Save Project");
        model.addAttribute("project", project);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        model.addAttribute("tabIndex", tabIndex);

        return "project";


    }


}
