package com.ys.eportal.controller;

import com.ys.eportal.infra.domain.CustomerEntity;
import com.ys.eportal.infra.domain.SalesOrderEntity;
import com.ys.eportal.mapper.CustomerMapper;
import com.ys.eportal.mapper.ProjectMapper;
import com.ys.eportal.model.Customer;
import com.ys.eportal.model.Project;
import com.ys.eportal.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProjectController {

    @Autowired
    private PortalService portalService;

    @Autowired
    private ProjectMapper projectMapper;

    @RequestMapping(value="/projectNew", method= RequestMethod.GET)
    public String ProjectForm(Model model) {

        Project project = new Project(new Customer());

        model.addAttribute("pageName", "New Project");
        model.addAttribute("project", project);
        return "projectNew";
    }

    @RequestMapping(value="/projectNew", method=RequestMethod.POST)
    public String projectSubmit(@ModelAttribute Project project, Model model) {

        SalesOrderEntity so = this.projectMapper.convert(project);
           so.setCustomerId(1);
        this.portalService.saveProject(so);

        project = this.projectMapper.convert(so);
        model.addAttribute("pageName", "Save Project");
        model.addAttribute("Project", so);
        return this.projectEditForm(so.getSalesOrderNumber(),"",model);//"projectEdit";
    }

    @RequestMapping(value="/projectedit", method= RequestMethod.GET)
    public String projectEditForm(@RequestParam(value="salesOrderNumber", required=true) Integer salesOrderNumber,
                                  @RequestParam(value="msgtype", required=false) String messageType,Model model) {



        SalesOrderEntity so = this.portalService.findSalesOrderEntityById(salesOrderNumber);
        Project project = this.projectMapper.convert(so);
        model.addAttribute("pageName", "Edit Project");
        model.addAttribute("project",new Project());
        return "projectEdit";
    }

    @RequestMapping(value="/projectEdit", method=RequestMethod.POST)
    public String projectEditSubmit(@ModelAttribute Project project, Model model) {

        //project = portalService.findProjectByID(1);
        // for now

        model.addAttribute("pageName", "Save Project");
        model.addAttribute("project", project);
        return "projectEdit";
    }

}
