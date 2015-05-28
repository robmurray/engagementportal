package com.ys.ui.controller;

import com.ys.ui.model.PageModelBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import com.ys.ui.model.Message;
import com.ys.ui.model.Navigation;

/**
 * Created by rob on 4/21/15.
 */
public abstract class ControllerBase {

    private static Logger logger = LoggerFactory.getLogger(ControllerBase.class);


    protected void addNav(Model model, String returnURL) {
        if (returnURL == null || returnURL.trim().equals("")) {
            returnURL = "projectSearch";
        }
        model.addAttribute("nav", new Navigation(returnURL));
    }

    protected void zzaddNav(Model model, String returnURL, String anchor) {
        if (returnURL == null || returnURL.trim().equals("")) {
            returnURL = "projectSearch";
        }
        Navigation nav = new Navigation();
        if (anchor != null) {

        }
        model.addAttribute("nav", nav);
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

    protected void addCommonPageAttributes(Model model, String modelName,Iterable<PageModelBase> pageModels,String pageName,String pageGroup, String pageId, String returnURL){
        this.addAllCommonPageAttributes(model, modelName,pageModels,pageName, pageGroup,pageId,returnURL);

    }

    protected void addCommonPageAttributes(Model model, String modelName,PageModelBase pageModel,String pageName,String pageGroup, String pageId, String returnURL){
        this.addAllCommonPageAttributes(model, modelName,pageModel,pageName, pageGroup,pageId,returnURL);

    }

    protected void addAllCommonPageAttributes(Model model, String modelName,Object pageModel,String pageName,String pageGroup, String pageId, String returnURL){
        model.addAttribute("pageName", pageName);
        model.addAttribute(modelName, pageModel);
        model.addAttribute("pageGroup", pageGroup);
        model.addAttribute("pageId", pageId);
        addNav(model, returnURL);

    }
    protected void addPageAttributes(Model model, String pageName, String subTitle) {
        model.addAttribute("subTitle", subTitle);
        model.addAttribute("pageName", pageName);
    }

}
