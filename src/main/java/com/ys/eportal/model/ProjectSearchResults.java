package com.ys.eportal.model;

import com.ys.eportal.infra.domain.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rob on 4/25/15.
 */
public class ProjectSearchResults {
    private Map<String,String> statusDisplayClass = new HashMap<String,String>(){{
        put("","danger");
        put(Constants.Projects.STATUS_BOOKED,"info");
        put(Constants.Projects.STATUS_COMPLETE,"primary");
        put(Constants.Projects.STATUS_INPROCESS,"success");
        put(Constants.Projects.STATUS_NOTDEFINED,"danger");
        put(Constants.Projects.STATUS_POSTSUPPORT,"danger");
        put(Constants.Projects.STATUS_PROPOSED,"default");
        put(Constants.Projects.STATUS_RANDSUPPORT,"warning");
        put(Constants.Projects.STATUS_SCHEDULED,"success");
    }};

    private long projectId;

    private String salesOrderNumber;

    private String customerName;

    private String status;


    public ProjectSearchResults() {
    }

    public ProjectSearchResults(long projectId, String salesOrderNumber, String customerName, String status) {
        this.projectId = projectId;
        this.salesOrderNumber = salesOrderNumber;
        this.customerName = customerName;
        this.status = status;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(String salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDisplayClass(){
        if(status==null){
            status ="";
        }
        return this.statusDisplayClass.get(status);
    }


}
