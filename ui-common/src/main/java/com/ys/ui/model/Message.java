package com.ys.ui.model;

/**
 * Created by rob on 4/21/15.
 */
public class Message {

    public static final String PRIMARY="primary";
    public static final String SUCCESS="success";
    public static final String DANGER="danger";
    public static final String WARNING="warning";
    public static final String INFO="info";


    private String type;
    private String message;
    private String gotoURL;

    public String getGotoURL() {
        return gotoURL;
    }

    public void setGotoURL(String gotoURL) {
        this.gotoURL = gotoURL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void setPrimary(){
        type=PRIMARY;
    }

    public void setSuccess(){
        type=SUCCESS;
    }

    public void setDanger(){
        type=DANGER;
    }

    public void setWarning(){
        type=WARNING;
    }

    public void setInfo(){
        type=INFO;
    }


}
