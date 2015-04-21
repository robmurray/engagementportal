package com.ys.eportal.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rob on 4/13/15.
 */
public class ModelConstants {
    public final static String STATUS_NOTDEFINED="undefined";
    public final static String STATUS_COMPLETE="Complete";
    public final static String STATUS_SCHEDULED="Scheduled";
    public final static String STATUS_RANDSUPPORT="R&D Support";
    public final static String STATUS_INPROCESS="In Process";
    public final static String STATUS_POSTSUPPORT="Post Support";
    public final static String STATUS_BOOKED="Booked";
    public final static String STATUS_PROPOSED="Proposed";

    protected static List<String> statusValues = new ArrayList<String>() {
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
