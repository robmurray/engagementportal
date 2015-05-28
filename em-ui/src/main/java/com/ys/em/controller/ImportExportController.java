package com.ys.em.controller;


import com.ys.em.infra.domain.ImportOracleObiStage;
import com.ys.em.infra.domain.UploadSalesOrder;
import com.ys.em.service.ImportService;
import com.ys.em.service.converter.ConversionResults;
import com.ys.em.model.ImportMasterStats;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class ImportExportController extends EportalBaseController {

    private static Logger logger = LoggerFactory.getLogger(ImportExportController.class);

    @Autowired
    private ImportService importService;


    @RequestMapping(value = "/uploadSalesOrder", method = RequestMethod.GET)
    public String uploadSalesOrderForm(Model model) {


        addPageAttributes(model, "Import Sales Order ", "import a sales order");

        model.addAttribute("uploadSalesOrder", new UploadSalesOrder());
        model.addAttribute("pageGroup", "importExport");
        model.addAttribute("pageId", "uploadSalesOrder");
        return "uploadSalesOrder";
    }

    @RequestMapping(value = "/uploadSalesOrder", method = RequestMethod.POST)
    public String soHandleFileUpload(@RequestParam("file") MultipartFile file, @ModelAttribute UploadSalesOrder uploadSalesOrder, BindingResult bindingResult, Model model) {
        ConversionResults<ImportOracleObiStage> results = null;
        try {

            if (file == null) {
                logger.debug("file not specified");
                bindingResult.rejectValue("fileData", "required_file", "file was not specified");

            }
            if (file != null && file.isEmpty()) {
                logger.debug("file empty");
                bindingResult.rejectValue("fileData", "required_file", "file is empty");

            }
            if (bindingResult.hasErrors()) {
                logger.debug("validation errors");
                return "uploadSalesOrder";
                //return this.soHandleFileUpload(file,uploadSalesOrder,bindingResult,model);
            }

            results = this.importService.importOracleOBICSVSalesOrder(file, uploadSalesOrder);

            logger.debug("record to process: " + results.getNumRecordsToProcess());
            logger.debug("record in error: " + results.getNumRecordsInError());
            logger.debug("records processed: " + results.getNumRecordsProcessed());

            if (results.getNumRecordsImported() > 0) {
                this.setSuccessAlertMessage(model, "import succeeded", "projectbatchid?batchid=" + results.getBatchId());
            } else {
                this.setWarningAlertMessage(model, "No records were imported");
            }
        } catch (Exception e) {
            logger.error("CSV IMPORT Failure", e);

            this.setDangerAlertMessage(model, "CSV IMPORT Failure");

            return this.uploadSalesOrderForm(model);
        }

        model.addAttribute("importResults", results);

        return this.uploadSalesOrderForm(model);
    }


    @RequestMapping(value = "/importMaster", method = RequestMethod.POST)
    public String importMasterFileUpload(@RequestParam("file") MultipartFile file, ImportMasterStats ims, BindingResult bindingResult, Model model) {

        try {

            if (file == null) {
                logger.debug("file not specified");
                bindingResult.rejectValue("fileData", "required_file", "file was not specified");

            }
            if (file != null && file.isEmpty()) {
                logger.debug("file empty");
                bindingResult.rejectValue("fileData", "required_file", "file is empty");

            }
            if (bindingResult.hasErrors()) {
                logger.debug("validation errors");
                return "importMaster";

            }

            this.importService.importMaster(file);
            this.setSuccessAlertMessage(model, "import succeeded");
        } catch (Exception e) {
            logger.error("CSV IMPORT Failure", e);

            this.setDangerAlertMessage(model, "CSV IMPORT Failure");

            return this.importMasterForm(model);
        }


        return this.importMasterForm(model);
    }



    @RequestMapping(value = "/importMaster", method = RequestMethod.GET)
    public String importMasterForm(Model model) {

        addPageAttributes(model, "Import Master", "import master spreadsheet admin only");

        model.addAttribute("pageGroup", "importExport");
        model.addAttribute("pageId", "importMaster");
        return "importMaster";
    }

    @RequestMapping(value = "/exportSalesOrders", method = RequestMethod.GET)
    public String exportSalesordersForm(Model model) {

        addPageAttributes(model, "Export Sales Order", "import master spreadsheet admin only");

        model.addAttribute("pageGroup", "importExport");
        model.addAttribute("pageId", "exportSalesOrders");
        return "exportSalesOrders";
    }


    protected void addPageAttributes(Model model, String pageName, String subTitle) {
        model.addAttribute("subTitle", subTitle);
        model.addAttribute("pageName", pageName);
    }
}
