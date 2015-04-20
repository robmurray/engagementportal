package com.ys.eportal.model;

import java.util.List;

/**
 * Created by rob on 4/20/15.
 */
public class Dashboard {

    private String notdefinedStatus = ModelConstants.STATUS_NOTDEFINED;
    private String completeStatus = ModelConstants.STATUS_COMPLETE;
    private String scheduledStatus = ModelConstants.STATUS_SCHEDULED;
    private String randSupportStatus = ModelConstants.STATUS_RANDSUPPORT;
    private String inprocessStatus= ModelConstants.STATUS_INPROCESS;
    private String postSupportStatus= ModelConstants.STATUS_POSTSUPPORT;
    private String bookedStatus= ModelConstants.STATUS_BOOKED;
    private String proposedStatus = ModelConstants.STATUS_PROPOSED;

    private List<String> statusValues = ModelConstants.statusValues;

    public List<String> getStatusValues() {
        return statusValues;
    }

    public void setStatusValues(List<String> statusValues) {
        this.statusValues = statusValues;
    }

    public String getNotdefinedStatus() {
        return notdefinedStatus;
    }

    public String getCompleteStatus() {
        return completeStatus;
    }

    public String getScheduledStatus() {
        return scheduledStatus;
    }

    public String getRandSupportStatus() {
        return randSupportStatus;
    }

    public String getInprocessStatus() {
        return inprocessStatus;
    }

    public String getPostSupportStatus() {
        return postSupportStatus;
    }

    public String getBookedStatus() {
        return bookedStatus;
    }

    public String getProposedStatus() {
        return proposedStatus;
    }


}
