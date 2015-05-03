package com.ys.eportal.controller;

import com.ys.eportal.infra.domain.*;
import com.ys.eportal.model.*;
import com.ys.eportal.service.PortalService;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.Set;

/**
 * Created by rob on 4/21/15.
 */
public abstract class ControllerBase {

    @Autowired
    protected PortalService portalService;


    public ProjectEntity retrieveProject(Project project) {
        if (project == null) {
            return null;
        }
        return this.portalService.findProjectByProjectId(project.getProjectId());
    }

    protected Long getDifferenceInDays(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return null;
        }
        boolean isNegative = false;
        Date param1 = date1;
        Date param2 = date2;

        // swap the dates if necessary
        if (date1.getTime() > date2.getTime()) {
            isNegative = true;
            param1 = date2;
            param2 = date1;
        }

        Interval interval = new Interval(param1.getTime(), param2.getTime());
        Duration d = interval.toDuration();

        return isNegative ? (-1) * d.getStandardDays() : d.getStandardDays();
    }

    protected void addNav(Model model, String returnURL) {
        if (returnURL == null || returnURL.trim().equals("")) {
            returnURL = "projectSearch";
        }
        model.addAttribute("nav", new Navigation(returnURL));
    }

    protected void addNav(Model model, String returnURL, String anchor) {
        if (returnURL == null || returnURL.trim().equals("")) {
            returnURL = "projectSearch";
        }
        Navigation nav = new Navigation();
        if (anchor != null) {

        }
        model.addAttribute("nav", nav);
    }

    public String retrieveUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        return name;
    }

    public void setSuccessAlertMessage(Model model, String message) {
        this.setSuccessAlertMessage(model, message, null);
    }

    public void setSuccessAlertMessage(Model model, String message, String gotoURL) {
        Message m = new Message();
        m.setSuccess();
        m.setMessage(message);
        m.setGotoURL(gotoURL);
        model.addAttribute("message", m);
    }

    public void setPrimaryAlertMessage(Model model, String message) {
        setPrimaryAlertMessage(model, message, null);
    }

    public void setPrimaryAlertMessage(Model model, String message, String gotoURL) {
        Message m = new Message();
        m.setPrimary();
        m.setMessage(message);
        m.setGotoURL(gotoURL);
        model.addAttribute("message", m);
    }

    public void setDangerAlertMessage(Model model, String message) {
        setDangerAlertMessage(model, message, null);
    }

    public void setDangerAlertMessage(Model model, String message, String gotoURL) {
        Message m = new Message();
        m.setDanger();
        m.setMessage(message);
        m.setGotoURL(gotoURL);
        model.addAttribute("message", m);
    }

    public void setInfoAlertMessage(Model model, String message) {
        setInfoAlertMessage(model, message, null);
    }

    public void setInfoAlertMessage(Model model, String message, String gotoURL) {
        Message m = new Message();
        m.setInfo();
        m.setMessage(message);
        m.setGotoURL(gotoURL);
        model.addAttribute("message", m);
    }

    public void setWarningAlertMessage(Model model, String message) {
        setWarningAlertMessage(model, message, null);
    }

    public void setWarningAlertMessage(Model model, String message, String gotoURL) {
        Message m = new Message();
        m.setWarning();
        m.setMessage(message);
        m.setGotoURL(gotoURL);
        model.addAttribute("message", m);
    }


    protected void addPageAttributes(Model model, String pageName, String subTitle) {
        model.addAttribute("subTitle", subTitle);
        model.addAttribute("pageName", pageName);
    }

    protected Project loadProject(ProjectEntity pe) {

        String projectName = "Project";
        Project project = null;
        // manually map for now
        if (pe != null) {

            project = new Project();


            project.setProjectId(project.getProjectId());
            project.setHealthStatus(pe.getHealth());

            // ready
            if (pe.getName() != null) {
                projectName = projectName + ":" + pe.getName();
            }


            // calculate @TODO push down into service layer
            if (pe.getKickoffMeetingDate() != null && pe.getBookDate() != null) {


                project.setBookedToKickOff(getDifferenceInDays(pe.getKickoffMeetingDate(), pe.getBookDate()));
            }

            if (pe.getReleaseForRevenueRecDate() != null && pe.getBookDate() != null) {


                project.setDaysToClose(getDifferenceInDays(pe.getReleaseForRevenueRecDate(), pe.getBookDate()));

            }

            project.setReadonly(true);
            project.setLocation(pe.getLocation());
            project.setName(pe.getName());
            project.setProjectId(pe.getProjectId());
            project.setService(pe.getService());
            project.setStatus(pe.getStatus());
            project.setWaitTime(pe.getWaitTime());

            Set<ProjectActivityEntity> paeList = pe.getProjectActivity();
            if (paeList != null) {
                for (ProjectActivityEntity pae : paeList) {
                    project.addActivity(new Activity(pae.getActivityId(), pae.getName(), pae.getDate(), pae.getStatus()));
                }
            }
            Set<ProjectResourceEntity> reList = pe.getProjectResources();
            if (reList != null) {

                for (ProjectResourceEntity pre : reList) {
                    if (pre.getRole().equals(Constants.Role.ONSITE)) {
                        project.addOnsiteResource(new Resource(pre.getResource().getResourceId(), pre.getProjectResourceId(), pre.getResource().getFirstName(), pre.getResource().getLastName(), pre.getResource().getType()));
                    } else if (pre.getRole().equals(Constants.Role.ACCOUNT)) {
                        project.addAccountResource(new Resource(pre.getResource().getResourceId(), pre.getProjectResourceId(), pre.getResource().getFirstName(), pre.getResource().getLastName(), pre.getResource().getType()));
                    } else if (pre.getRole().equals(Constants.Role.REMOTE)) {
                        project.addRemoteResource(new Resource(pre.getResource().getResourceId(), pre.getProjectResourceId(), pre.getResource().getFirstName(), pre.getResource().getLastName(), pre.getResource().getType()));
                    }
                }
            }
            Iterable<ResourceEntity> allResources = this.portalService.findAllResources();
            if (allResources != null) {
                for (ResourceEntity pre : allResources) {
                    project.addRemoteAddableResource(new Resource(pre.getResourceId(), -1, pre.getFirstName(), pre.getLastName(), pre.getType()));
                }
            }
            Set<ProjectNotesEntity> pneList = pe.getNotes();
            if (pneList != null) {

                for (ProjectNotesEntity pne : pneList) {
                    project.addNotes(new ProjectNote(pne.getNoteId(), pne.getNotes(), pne.getCreateDate()));
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

}
