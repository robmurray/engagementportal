package com.ys.eportal.controller;

import com.ys.eportal.model.Customer;
import com.ys.eportal.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class UploadController {

    @Autowired
    private PortalService portalService;

    @RequestMapping(value = "/uploadso", method = RequestMethod.GET)
    public
    @ResponseBody
    String soUploadForm(Model model) {

        Customer customer = new Customer();
        addPageAttributes(model, "Upload Sales Orders", "upload sales order csv files");

        model.addAttribute("customer", customer);
        return "uploadSo";
    }

    protected void addPageAttributes(Model model, String pageName, String subTitle) {
        model.addAttribute("subTitle", subTitle);
        model.addAttribute("pageName", pageName);
    }

    @RequestMapping(value = "/uploadso", method = RequestMethod.POST)
    public
    @ResponseBody
    String soHandleFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }

}