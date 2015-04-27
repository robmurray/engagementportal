package com.ys.eportal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rob on 4/25/15.
 */
public class Activity extends AbstractModelBase{

    public static Activity BOOK_DATE = new Activity("bookDate");
    public static Activity SHIP_DATE = new Activity("shipDate");
    public static Activity REVREC_DATE = new Activity("releaseForRevenueRecDate");
    public static Activity ONSITEEND_DATE = new Activity("onSiteEndDate");
    public static Activity ONSITESTART_DATE = new Activity("onSiteStartDate");
    public static Activity KICKOFF_DATE = new Activity("kickoffMeetingDate");
    public static Activity PLANNINGMEETING_DATE = new Activity("planningMeetingDate");

    private long activityId;
    private String name;
    private Date date;
    private String status;

    private List<String> activityStatusValues = new ArrayList<String>() {{
        add("NOT STARTED");
        add("BOOKED");
        add("COMPLETE");
        add("WARNING");
        add("BLOCKED");
    }};
    public Activity() {
    }

    public Activity(String name) {
        this.name = name;
    }

    public Activity(String name, Date date, String status) {
        this.name = name;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
