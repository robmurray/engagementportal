package com.ys.eportal.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Created by rob on 4/6/15.
 */
public class UploadSalesOrder {
    public static String DEFAULT_MODEL_GROUPS = "VPN,PNA,CSN,WLAN,ASPRO";
    private String modelgroups;
    private CommonsMultipartFile fileData;


    public UploadSalesOrder() {
        this.modelgroups = DEFAULT_MODEL_GROUPS;
    }

    public CommonsMultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }

    public String getModelgroups() {
        return modelgroups;
    }

    public void setModelgroups(String modelgroups) {
        this.modelgroups = modelgroups;
    }
}
