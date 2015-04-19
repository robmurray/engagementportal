package com.ys.eportal.controller;


import com.ys.eportal.infra.domain.ImportOracleObiStage;
import com.ys.eportal.model.CustomerSearch;
import com.ys.eportal.model.FileUpload;
import com.ys.eportal.model.ImportOracleObi;
import com.ys.eportal.model.UploadSalesOrder;
import com.ys.eportal.service.PortalService;
import com.ys.eportal.service.converter.CSV2SalesOrderConverter;
import com.ys.eportal.service.converter.ConversionResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.TreeMap;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class ImportExportController {

    private static Logger logger = LoggerFactory.getLogger(ImportExportController.class);

    @Autowired
    private PortalService portalService;


    @RequestMapping(value = "/uploadSalesOrder", method = RequestMethod.GET)
    public String uploadSalesOrderForm(Model model) {


        addPageAttributes(model, "Import Sales Order ", "import a sales order");

        model.addAttribute("uploadSalesOrder",new UploadSalesOrder());
        model.addAttribute("pageGroup", "importExport");
        model.addAttribute("pageId", "uploadSalesOrder");
        return "uploadSalesOrder";
    }

    @RequestMapping(value = "/uploadSalesOrder", method = RequestMethod.POST)
    public String soHandleFileUpload(@RequestParam("file") MultipartFile file,@ModelAttribute UploadSalesOrder uploadSalesOrder, Model model){
        ConversionResults<String,ImportOracleObiStage> results = null;
        try {

            results = this.portalService.importOracleOBICSVSalesOrder(file);

            logger.debug("record to process: "+results.getNumRecordsToProcess());
            logger.debug("record in error: "+results.getNumRecordsInError());
            logger.debug("records processed: "+results.getNumRecordsProcessed());


        } catch (Exception e) {
            logger.error("CSV IMPORT Failure", e);
        }


        model.addAttribute("importResults",results);
        return "redirect:projectSearch";
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

        addPageAttributes(model, "Import Master", "import master spreadsheet admin only");

        model.addAttribute("pageGroup", "importExport");
        model.addAttribute("pageId", "exportSalesOrders");
        return "exportSalesOrders";
    }


    protected void addPageAttributes(Model model, String pageName, String subTitle) {
        model.addAttribute("subTitle", subTitle);
        model.addAttribute("pageName", pageName);
    }
}
