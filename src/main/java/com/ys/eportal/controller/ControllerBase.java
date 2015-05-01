package com.ys.eportal.controller;

import com.ys.eportal.infra.domain.Constants;
import com.ys.eportal.infra.domain.ProjectEntity;
import com.ys.eportal.model.Message;
import com.ys.eportal.model.Navigation;
import com.ys.eportal.model.Project;
import com.ys.eportal.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rob on 4/21/15.
 */
public abstract class ControllerBase {

    @Autowired
    protected PortalService portalService;




    public ProjectEntity retrieveProject(Project project){
        if(project ==null){
            return null;
        }
        return this.portalService.findProjectByProjectId(project.getProjectId());
    }


    protected void addNav(Model model, String returnURL){
        if(returnURL==null || returnURL.trim().equals("")){
            returnURL="projectSearch";
        }
        model.addAttribute("nav", new Navigation(returnURL));
    }

    protected void addNav(Model model, String returnURL,String anchor){
        if(returnURL==null || returnURL.trim().equals("")){
            returnURL="projectSearch";
        }
        Navigation nav = new Navigation();
        if(anchor!=null){

        }
        model.addAttribute("nav", nav);
    }

    public String retrieveUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        return  name;
    }

    public void setSuccessAlertMessage(Model model,String message) {
        this.setSuccessAlertMessage(model,message,null);
    }

    public void setSuccessAlertMessage(Model model,String message,String gotoURL){
        Message m = new Message();
        m.setSuccess();
        m.setMessage(message);
        m.setGotoURL(gotoURL);
        model.addAttribute("message", m);
    }

    public void setPrimaryAlertMessage(Model model,String message){
        setPrimaryAlertMessage(model,message,null);
    }

    public void setPrimaryAlertMessage(Model model,String message,String gotoURL){
        Message m = new Message();
        m.setPrimary();
        m.setMessage(message);
        m.setGotoURL(gotoURL);
        model.addAttribute("message", m);
    }

    public void setDangerAlertMessage(Model model,String message){
        setDangerAlertMessage(model,message,null);
    }
    public void setDangerAlertMessage(Model model,String message,String gotoURL){
        Message m = new Message();
        m.setDanger();
        m.setMessage(message);
        m.setGotoURL(gotoURL);
        model.addAttribute("message", m);
    }

    public void setInfoAlertMessage(Model model,String message){
        setInfoAlertMessage(model,message,null);
    }

    public void setInfoAlertMessage(Model model,String message,String gotoURL){
        Message m = new Message();
        m.setInfo();
        m.setMessage(message);
        m.setGotoURL(gotoURL);
        model.addAttribute("message", m);
    }
    public void setWarningAlertMessage(Model model,String message){
        setWarningAlertMessage(model,message,null);
    }
    public void setWarningAlertMessage(Model model,String message,String gotoURL){
        Message m = new Message();
        m.setWarning();
        m.setMessage(message);
        m.setGotoURL(gotoURL);
        model.addAttribute("message", m);
    }

}
