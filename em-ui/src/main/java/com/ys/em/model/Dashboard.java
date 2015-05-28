package com.ys.em.model;

import com.ys.em.infra.domain.Constants;

import java.util.List;

/**
 * Created by rob on 4/20/15.
 */
public class Dashboard {

    private String notdefinedStatus = Constants.Projects.STATUS_NOTDEFINED;
    private String completeStatus = Constants.Projects.STATUS_COMPLETE;
    private String scheduledStatus = Constants.Projects.STATUS_SCHEDULED;
    private String randSupportStatus = Constants.Projects.STATUS_RANDSUPPORT;
    private String inprocessStatus= Constants.Projects.STATUS_INPROCESS;
    private String postSupportStatus= Constants.Projects.STATUS_POSTSUPPORT;
    private String bookedStatus= Constants.Projects.STATUS_BOOKED;
    private String proposedStatus = Constants.Projects.STATUS_PROPOSED;

    private List<String> statusValues = Constants.Projects.statusValues;

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
