package com.ys.eportal.model;

import com.ys.core.infra.domain.ep.Constants;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by rob on 4/25/15.
 */
public class Activity extends AbstractModelBase{

    private long activityId;
    private String name;


    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date activityDate;
    private String status;

    private List<String> activityStatusValues = Constants.Activities.ActivityStateValues;

    public Activity() {
    }

    public Activity(String name) {
        this.name = name;
    }

    public Activity(String name, Date activityDate, String status) {
        this.name = name;
        this.activityDate=activityDate;
        this.status = status;
    }

    public Activity(long activityId, String name, Date activityDate, String status) {
        this.activityId = activityId;
        this.name = name;
        this.activityDate=activityDate;
        this.status = status;
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getActivityStatusValues() {
        return activityStatusValues;
    }

    public void setActivityStatusValues(List<String> activityStatusValues) {
        this.activityStatusValues = activityStatusValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;

        Activity activity = (Activity) o;

        if (activityId != activity.activityId) return false;

        return true;
    }


    @Override
    public int hashCode() {
        return (int) (activityId ^ (activityId >>> 32));
    }
}
