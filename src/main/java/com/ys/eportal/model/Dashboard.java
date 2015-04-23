package com.ys.eportal.model;

import com.ys.eportal.infra.domain.Constants;

import java.util.List;

/**
 * Created by rob on 4/20/15.
 */
public class Dashboard {

    private String notdefinedStatus = Constants.SalesOrders.STATUS_NOTDEFINED;
    private String completeStatus = Constants.SalesOrders.STATUS_COMPLETE;
    private String scheduledStatus = Constants.SalesOrders.STATUS_SCHEDULED;
    private String randSupportStatus = Constants.SalesOrders.STATUS_RANDSUPPORT;
    private String inprocessStatus= Constants.SalesOrders.STATUS_INPROCESS;
    private String postSupportStatus= Constants.SalesOrders.STATUS_POSTSUPPORT;
    private String bookedStatus= Constants.SalesOrders.STATUS_BOOKED;
    private String proposedStatus = Constants.SalesOrders.STATUS_PROPOSED;

    private List<String> statusValues = Constants.SalesOrders.statusValues;

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
