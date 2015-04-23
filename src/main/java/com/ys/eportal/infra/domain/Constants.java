package com.ys.eportal.infra.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rob on 4/13/15.
 */
public class Constants {
    public static class SalesOrders {
        public final static String STATUS_NOTDEFINED = "undefined";
        public final static String STATUS_COMPLETE = "Complete";
        public final static String STATUS_SCHEDULED = "Scheduled";
        public final static String STATUS_RANDSUPPORT = "R&D Support";
        public final static String STATUS_INPROCESS = "In Process";
        public final static String STATUS_POSTSUPPORT = "Post Support";
        public final static String STATUS_BOOKED = "Booked";
        public final static String STATUS_PROPOSED = "Proposed";

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
}