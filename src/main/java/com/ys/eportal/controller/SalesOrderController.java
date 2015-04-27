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
import java.util.Set;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class SalesOrderController extends ControllerBase {
    private static Logger logger = LoggerFactory.getLogger(SalesOrderController.class);
    @Autowired
    private PortalService portalService;

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yy");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }


    @RequestMapping(value = "/salesorder", method = RequestMethod.GET)
    public String projectView(@RequestParam(value = "salesorderId", required = true) long salesorderId,
                              @RequestParam(value = "returnURL", required = false) String returnURL,
                              @RequestParam(value = "msgtype", required = false) String messageType, Model model) {

        SalesOrderEntity soe = this.portalService.findSalesOrderEntityById(salesorderId);
        SalesOrder so = null;
        // manually map for now
        if (soe != null) {
/*
            Set<ProjectNotesEntity> pneList =pe.getNotes();
            if(pneList !=null){

                for(ProjectNotesEntity pne:pneList){
                    project.addNotes(new ProjectNote(pne.getNotes(),pne.getCreateDate()));
                }
            }
  */
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
            }
        addNav(model,returnURL);
        model.addAttribute("pageName", "SaleOrder");
        model.addAttribute("salesorder", so);

        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "salesorder";
    }
}
