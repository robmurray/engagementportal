package com.ys.eportal.infra.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rob on 4/13/15.
 */
public class Constants {

    public final static List<String> modelGroupValues = new ArrayList<String>() {{
        add("WLAN");
        add("VPM");
        add("CSN");
    }};

    public static class Projects {
        public final static String STATUS_NOTDEFINED = "undefined";
        public final static String STATUS_COMPLETE = "Complete";
        public final static String STATUS_SCHEDULED = "Scheduled";
        public final static String STATUS_RANDSUPPORT = "R&D Support";
        public final static String STATUS_INPROCESS = "In Process";
        public final static String STATUS_POSTSUPPORT = "Post Support";
        public final static String STATUS_BOOKED = "Booked";
        public final static String STATUS_PROPOSED = "Proposed";

        public final static String HEALTH_STATUS_GOOD = "good";
        public final static String HEALTH_STATUS_WARNING = "warning";
        public final static String HEALTH_STATUS_RISK = "at risk";

        public static List<String> healthStatusValues = new ArrayList<String>() {
            {
                add(HEALTH_STATUS_GOOD);
                add(HEALTH_STATUS_WARNING);
                add(HEALTH_STATUS_GOOD);

            }
        };
        public static List<String> statusValues = new ArrayList<String>() {
            {
                add(STATUS_NOTDEFINED);
                add(STATUS_COMPLETE);
                add(STATUS_SCHEDULED);
                add(STATUS_RANDSUPPORT);
                add(STATUS_INPROCESS);
                add(STATUS_POSTSUPPORT);
                add(STATUS_BOOKED);
                add(STATUS_PROPOSED);
            }
        };
    }


    public static class Credits {
        public final static String STATUS_HI_HOLD = "HI HOLD";
        public final static String STATUS_DEBOOKED = "DEBOOKED";
        public final static String STATUS_FREE = "FREE";
        public final static String STATUS_HI_HOLD_MLANEVIL = "HI HOLD_Mlanevil";
        public final static String STANDARD = "STANDARD";
        public final static String NOTSPECIFIED = "NOT SPECIFIED";
        public static List<String> statusValues = new ArrayList<String>() {
            {
                add(STANDARD);
                add(STATUS_HI_HOLD);
                add(STATUS_DEBOOKED);
                add(STATUS_FREE);
                add(STATUS_HI_HOLD_MLANEVIL);

            }
        };


    }
    public static class Role{
        public final static String ACCOUNT="account team";
        public final static String ONSITE="onsite";
        public final static String REMOTE="remote";
    }


    public static class Activities{

        public static String BOOK_DATE ="bookDate";
        public static String  SHIP_DATE ="shipDate";
        public static String  REVREC_DATE ="releaseForRevenueRecDate";
        public static String  ONSITEEND_DATE = "onSiteEndDate";
        public static String  ONSITESTART_DATE = "onSiteStartDate";
        public static String  KICKOFF_DATE = "kickoffMeetingDate";
        public static String  PLANNINGMEETING_DATE ="planningMeetingDate";

    }
}