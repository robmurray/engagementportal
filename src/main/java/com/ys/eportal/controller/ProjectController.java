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
import java.util.Date;
import java.util.Set;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class ProjectController extends ControllerBase {
    private static Logger logger = LoggerFactory.getLogger(ProjectController.class);
    @Autowired
    private PortalService portalService;

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yy");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }


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
        model.addAttribute("anchor", "noteForm");
        model.addAttribute("returnURL", "project?projectId="+projectId);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "project";
    }

    private Project loadProject(ProjectEntity pe){

        String projectName = "Project";
        Project project = null;
        // manually map for now
        if (pe != null) {

            project = new Project();

            // @TODO need to hook up
            project.setHealthStatus(pe.getHealth());

            // ready
            if (pe.getName() != null) {
                projectName = projectName + ":" + pe.getName();
            }
            project.setBookedToKickOff(pe.getBookedToKickOff());
            project.setDaysToClose(pe.getDaysToClose());
            project.setLocation(pe.getLocation());
            project.setName(pe.getName());
            project.setProjectId(pe.getProjectId());
            project.setService(pe.getService());
            project.setStatus(pe.getStatus());
            project.setWaitTime(pe.getWaitTime());

            Set<ProjectActivityEntity> paeList = pe.getProjectActivity();
            if (paeList != null) {
                for (ProjectActivityEntity pae : paeList) {
                    project.addActivity(new Activity(pae.getActivityId(),pae.getName(), pae.getDate(), pae.getStatus()));
                }
            }
            Set<ProjectResourceEntity> reList = pe.getProjectResources();
            if (reList != null) {

                for (ProjectResourceEntity pre : reList) {
                    if (pre.getRole().equals(Constants.Role.ONSITE)) {
                        project.addOnsiteResource(new Resource(pre.getResource().getFirstName(), pre.getResource().getLastName(),pre.getResource().getType()));
                    } else if (pre.getRole().equals(Constants.Role.ACCOUNT)) {
                        project.addAccountResource(new Resource(pre.getResource().getFirstName(), pre.getResource().getLastName(),pre.getResource().getType()));
                    } else if (pre.getRole().equals(Constants.Role.REMOTE)) {
                        project.addRemoteResource(new Resource(pre.getResource().getFirstName(), pre.getResource().getLastName(),pre.getResource().getType()));
                    }
                }
            }
            Set<ProjectNotesEntity> pneList =pe.getNotes();
            if(pneList !=null){

                for(ProjectNotesEntity pne:pneList){
                    project.addNotes(new ProjectNote(pne.getNotes(),pne.getCreateDate()));
                }
            }
            SalesOrderEntity soe = pe.getSalesOrders();
            SalesOrder so = null;
            if (soe != null) {
                so = new SalesOrder();
                //so.setActivityDate();
                so.setAmount(soe.getAmount());
                so.setBtCustomerName(soe.getBtCustomerName());
                so.setContractStatusCode(soe.getContractStatusCode());
                so.setEndUserName(soe.getEndUserName());
                so.setForecastGroupCode(soe.getForecastGroupCode());
                so.setImportControlId(soe.getImportControlId());
                so.setModelGroup(soe.getModelGroup());
                so.setOrderedQuantity(soe.getOrderedQuantity());
                so.setProductFamilyCode(soe.getProductFamilyCode());
                so.setRegion(soe.getRegion());
                so.setSalesOrderId(soe.getSalesOrderId());
                so.setSalesOrderNumber(soe.getSalesOrderNumber());
                so.setStChannelName(soe.getStChannelName());

                if (soe.getCustomer() != null) {
                    // @TODO should never happen. would not get to this point
                    // this means the data is bad
                    // but will need to add somethign in here to cover edge case
                    so.setStCustomerName(soe.getCustomer().getName());
                    so.setCustomerId(soe.getCustomer().getCustomerId());
                }
                so.setStSalesAgentName(soe.getStSalesAgentName());
                project.addSalesOrder(so);
            }


        }
        return project;
    }

   @RequestMapping(value="/projectInfo", method=RequestMethod.POST)
    public String projectEditSubmit(@ModelAttribute Project project, Model model) {




        //this.portalService.saveProject(this.projectMapper.convert(project));
        this.setSuccessAlertMessage(model,"project updated");
        model.addAttribute("pageName", "Save Project");
        model.addAttribute("project", project);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "project";
    }

    @RequestMapping(value="/projectNote", method=RequestMethod.POST)
    public String projectNoteSubmit(@RequestParam(value="id",required=false) long id,@RequestParam(value="newnote",required=false) String newnote, Model model) {


        ProjectEntity pe = this.portalService.findProjectByProjectId(id);

        if(pe !=null) {
            ProjectNotesEntity pne = new ProjectNotesEntity(pe, newnote);
            this.portalService.addNote(pne);
            pe = this.portalService.findProjectByProjectId(id);
        }
        Project project = this.loadProject(pe);

        //this.setSuccessAlertMessage(model,"project updated");
        model.addAttribute("pageName", "Save Project");
        model.addAttribute("project", project);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");

        //return new ModelAndView(new RedirectView("project#notesForm", true));


        return projectView(id,"","", model);

            //    return "redirect:project#notesForm?projectId="+id;
    }

}
