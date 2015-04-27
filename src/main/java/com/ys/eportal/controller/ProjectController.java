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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

    @RequestMapping(value = "/project1", method = RequestMethod.GET)
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

        project.addAccountResource(new Resource("Bill", "Smith"));
        project.addAccountResource(new Resource("will", "the Fifth"));
        project.addAccountResource(new Resource("Tom", "Wilsom"));

        project.addRemoteResource(new Resource("Ed", "Rickson"));
        project.addRemoteResource(new Resource("Tim", "Murray"));
        project.addRemoteResource(new Resource("Jill", "Donaldsom"));

        project.addOnsiteResource(new Resource("Bill", "Paxton"));
        project.addOnsiteResource(new Resource("Angie", "Perry"));
        project.addOnsiteResource(new Resource("Terry", "Sam"));

        model.addAttribute("pageName", "Project");
        model.addAttribute("project", project);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "createProject");
        return "project";
    }

    /*

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



            SalesOrderEntity so = null;//this.projectMapper.convert(project);
         //   so.setCustomer(new CustomerEntity(project.getCustomerId()));
            this.portalService.saveProject(so);

            //project = this.projectMapper.convert(so);
            model.addAttribute("pageName", "Save Project");
            model.addAttribute("Project", so);
            model.addAttribute("pageGroup", "project");
            model.addAttribute("pageId", "createProject");
            this.setSuccessAlertMessage(model,"project created");
            return this.projectEditForm(so.getSalesOrderId(),"",model);//"projectEdit";
        }

    */
    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public String projectView(@RequestParam(value = "projectId", required = true) long projectId,
                              @RequestParam(value = "msgtype", required = false) String messageType, Model model) {


        ProjectEntity pe = this.portalService.findProjectByProjectId(projectId);
        String projectName = "Project";
        Project project = null;
        // manually map for now
        if (pe != null) {

            project = new Project();

            // @TODO need to hook up
            project.setHealthStatus("green");

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
                    project.addActivity(new Activity(pae.getName(), pae.getDate(), pae.getStatus()));
                }
            }
            Set<ProjectResourceEntity> reList = pe.getProjectResources();
            if (reList != null) {

                for (ProjectResourceEntity pre : reList) {
                    if (pre.getRole().equals(Constants.Role.ONSITE)) {
                        project.addOnsiteResource(new Resource(pre.getResource().getFirstName(), pre.getResource().getLastName()));
                    } else if (pre.getRole().equals(Constants.Role.ACCOUNT)) {
                        project.addAccountResource(new Resource(pre.getResource().getFirstName(), pre.getResource().getLastName()));
                    } else if (pre.getRole().equals(Constants.Role.REMOTE)) {
                        project.addRemoteResource(new Resource(pre.getResource().getFirstName(), pre.getResource().getLastName()));
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
                }
                so.setStSalesAgentName(soe.getStSalesAgentName());
                project.addSalesOrder(so);
            }


        }


        model.addAttribute("pageName", "Project");
        model.addAttribute("project", project);

        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "project";
    }



/*

    @RequestMapping(value="/projectEdit", method= RequestMethod.GET)
    public String projectEditForm(@RequestParam(value="salesOrderId", required=true) long salesOrderId,
                                  @RequestParam(value="msgtype", required=false) String messageType,Model model) {

        SalesOrderEntity so = this.portalService.findSalesOrderEntityById(salesOrderId);
        Project project = null;//this.projectMapper.convert(so);
        model.addAttribute("pageName", "Edit Project");
        model.addAttribute("project",project);

        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "projectEdit";
    }

    @RequestMapping(value="/projectEdit", method=RequestMethod.POST)
    public String projectEditSubmit(@ModelAttribute Project project, Model model) {

        //this.portalService.saveProject(this.projectMapper.convert(project));
        this.setSuccessAlertMessage(model,"project updated");
        model.addAttribute("pageName", "Save Project");
        model.addAttribute("project", project);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "projectEdit";
    }

*/
}
