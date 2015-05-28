package com.ys.em.controller;

import com.ys.em.infra.domain.*;
import com.ys.em.model.*;
import com.ys.em.service.PortalService;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.Date;
import java.util.Set;

/**
 * Created by rob on 4/21/15.
 */
@Import(PortalService.class)
public abstract class EportalBaseController extends com.ys.ui.controller.ControllerBase {

    private static Logger logger = LoggerFactory.getLogger(EportalBaseController.class);

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

            project.setReadonly(true);
            project.setLocation(pe.getLocation());
            project.setName(pe.getName());
            project.setProjectId(pe.getProjectId());
            project.setService(pe.getService());
            project.setStatus(pe.getStatus());
            project.setWaitTime(pe.getWaitTime());

            project.setCredits(pe.getCredits());
            Date bookDate = null;
            Date kickOffDate = null;
            Date resForRevDate = null;

            Set<ProjectActivityEntity> paeList = pe.getProjectActivity();
            if (paeList != null) {
                for (ProjectActivityEntity pae : paeList) {
                    project.addActivity(new Activity(pae.getActivityId(), pae.getName(), pae.getDate(), pae.getStatus()));
                    if (Constants.Activities.BOOK_DATE.equals(pae.getName())) {
                        bookDate = pae.getDate();
                    } else if (Constants.Activities.KICKOFF_DATE.equals(pae.getName())) {
                        kickOffDate = pae.getDate();
                    } else if (Constants.Activities.REVREC_DATE.equals(pae.getName())) {
                        resForRevDate = pae.getDate();
                    }

                    if (kickOffDate != null && bookDate != null) {
                        project.setBookedToKickOff(getDifferenceInDays(kickOffDate, bookDate));
                    }

                    if (resForRevDate != null && bookDate != null) {
                        project.setDaysToClose(getDifferenceInDays(resForRevDate, bookDate));
                    }

                }
            }
            Set<ProjectResourceEntity> reList = pe.getProjectResources();
            if (reList != null) {
                Resource newres = null;
                for (ProjectResourceEntity pre : reList) {
                    newres = new Resource(pre.getResource().getResourceId(), pre.getProjectResourceId(), pre.getResource().getFirstName(), pre.getResource().getLastName(), pre.getResource().getType(),pre.getRole());
                    project.addResource(newres);
                }
            }

            Iterable<ResourceEntity> allResources = this.portalService.findAllResources();
            if (allResources != null) {
                Resource newResn = null;
                for (ResourceEntity pre : allResources) {
                    newResn = new Resource(pre.getResourceId(), -1, pre.getFirstName(), pre.getLastName(), pre.getType(),"");
                    project.addAvailableResource(newResn);
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
                project.setSalesOrder(so);
            }


        }
        return project;
    }

}
