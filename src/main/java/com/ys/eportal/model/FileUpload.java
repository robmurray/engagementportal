package com.ys.eportal.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUpload {

    private CommonsMultipartFile fileData;


    public CommonsMultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }
}
