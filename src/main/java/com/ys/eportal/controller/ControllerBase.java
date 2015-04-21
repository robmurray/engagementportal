package com.ys.eportal.controller;

import com.ys.eportal.model.Message;
import org.springframework.ui.Model;

/**
 * Created by rob on 4/21/15.
 */
public abstract class ControllerBase {

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
