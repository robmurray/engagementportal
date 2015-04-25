package com.ys.eportal.controller;

import com.ys.eportal.infra.domain.SalesOrderEntity;
import com.ys.eportal.mapper.ProjectMapper;
import com.ys.eportal.model.*;
import com.ys.eportal.service.PortalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class ProjectController  extends ControllerBase{
    private static Logger logger = LoggerFactory.getLogger(ProjectController.class);
    @Autowired
    private PortalService portalService;

    @Autowired
    private ProjectMapper projectMapper;

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yy");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping(value="/project", method= RequestMethod.GET)
    public String projectDetailForm(Model model) {

        Project project = new Project();
        project.addSalesOrder(new SalesOrder());

        project.addActivity(Activity.PLANNINGMEETING_DATE);
        project.addActivity(Activity.KICKOFF_DATE);
        project.addActivity(Activity.BOOK_DATE);
        project.addActivity(Activity.ONSITEEND_DATE);
        project.addActivity(Activity.ONSITESTART_DATE);
        project.addActivity(Activity.SHIP_DATE);
        project.addActivity(Activity.REVREC_DATE);


        project.addNotes(new ProjectNote("test note"));
        project.addNotes(new ProjectNote("test note2"));
        project.addNotes(new ProjectNote("test note3"));

        model.addAttribute("pageName", "Project");
        model.addAttribute("project", project);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "createProject");
        return "project";
    }



    @RequestMapping(value="/projectNew", method= RequestMethod.GET)
    public String projectForm(Model model) {

        Project project = new Project();

        model.addAttribute("customers", this.portalService.findAllCustomers());

        model.addAttribute("pageName", "New Project");
        model.addAttribute("project", project);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "createProject");
        return "projectNew";
    }

    @RequestMapping(value="/projectNew", method=RequestMethod.POST)
    public String projectSubmit(@Valid Project project,BindingResult bindingResult, Model model) {


        // look for dup
//        if (project != null && StringUtils.isNotEmpty(project.getSalesOrderNumber())) {

            // @todo need to indicate to the user that there is already a salesnumber woth
            // that number. do you want to continue

           //SalesOrderEntity e =  this.portalService.findSalesOrderEntityById(project.getSalesOrderNumber());
            //if(e !=null) {
            //    logger.debug("duplicate sales order number error");
             //   bindingResult.rejectValue("salesOrderNumber", "duplicate_son", "duplicate sales order number");
            //}
//        }

        if (bindingResult.hasErrors()) {
            logger.debug("validation errors");
            model.addAttribute("customers", this.portalService.findAllCustomers());

            return "projectNew";
        }



        SalesOrderEntity so = this.projectMapper.convert(project);
     //   so.setCustomer(new CustomerEntity(project.getCustomerId()));
        this.portalService.saveProject(so);

        project = this.projectMapper.convert(so);
        model.addAttribute("pageName", "Save Project");
        model.addAttribute("Project", so);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "createProject");
        this.setSuccessAlertMessage(model,"project created");
        return this.projectEditForm(so.getSalesOrderId(),"",model);//"projectEdit";
    }

    @RequestMapping(value="/projectEdit", method= RequestMethod.GET)
    public String projectEditForm(@RequestParam(value="salesOrderId", required=true) long salesOrderId,
                                  @RequestParam(value="msgtype", required=false) String messageType,Model model) {

        SalesOrderEntity so = this.portalService.findSalesOrderEntityById(salesOrderId);
        Project project = this.projectMapper.convert(so);
        model.addAttribute("pageName", "Edit Project");
        model.addAttribute("project",project);

        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "projectEdit";
    }

    @RequestMapping(value="/projectEdit", method=RequestMethod.POST)
    public String projectEditSubmit(@ModelAttribute Project project, Model model) {

        this.portalService.saveProject(this.projectMapper.convert(project));
        this.setSuccessAlertMessage(model,"project updated");
        model.addAttribute("pageName", "Save Project");
        model.addAttribute("project", project);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "projectEdit";
    }

}
