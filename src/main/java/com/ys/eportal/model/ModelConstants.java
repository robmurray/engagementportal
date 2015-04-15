package com.ys.eportal.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rob on 4/13/15.
 */
public class ModelConstants {

    protected static List<String> statusValues = new ArrayList<String>() {
        {
            add("Complete");
            add("Scheduled");
            add("R&D Support");
            add("In Process");
            add("Post support");
            add("Booked");
            add("Proposed");
        }
    };


}
