package com.ys.eportal.controller;

import com.ys.eportal.infra.domain.ProjectEntity;
import com.ys.eportal.infra.domain.ResourceEntity;
import com.ys.eportal.mapper.ProjectSearchMapper;
import com.ys.eportal.model.ProjectSearch;
import com.ys.eportal.model.ProjectSearchResults;
import com.ys.eportal.model.Resource;
import com.ys.eportal.model.ResourceSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class ResourceSearchController extends ResourceControllerBase {
    private static Logger logger = LoggerFactory.getLogger(ResourceSearchController.class);


    @RequestMapping(value = "/resourceProjectid", method = RequestMethod.GET)
    public String resosurceSearchForm3(@RequestParam(value = "projectid", required = true) long projectId, Model model) {
        ResourceSearch search = new ResourceSearch();
    //    search.setImportControlId(batchId);
        return this.submitSearchForm(search, model);
    }

    @RequestMapping(value = "/resourceType", method = RequestMethod.GET)
    public String resourceSearchForm2(@RequestParam(value = "type", required = true) String type, Model model) {
        ResourceSearch search = new ResourceSearch();
        search.setType(type);
        return this.submitSearchForm(search, model);
    }

    @RequestMapping(value = "/resourceSearch", method = RequestMethod.GET)
    public String resourceSearchForm(Model model) {

        Iterable<ResourceEntity> wrkList = this.portalService.findAllResources();
        List<Resource> returnList = this.load(wrkList);

        ResourceSearch resource =new ResourceSearch();

        addNav(model,"resourceSearch");
        model.addAttribute("pageName", "Resource Search");
        model.addAttribute("resourcesearch", resource);
        model.addAttribute("resources", returnList);
        model.addAttribute("pageGroup", "reosurce");
        model.addAttribute("pageId", "searchResource");
        return "resourceSearch";
    }


    @RequestMapping(value = "/resourceSearch", method = RequestMethod.POST)
    public String submitSearchForm(@ModelAttribute ResourceSearch search, Model model) {


        model.addAttribute("pageName", "Resource Search");


        //@TODO map framework
        List<ProjectSearchResults> returnList = null;

        if (search != null) {

            //Iterable<ResourceEntity> wrkList = this.portalService.find(null);

            //returnList = buildSearchResults(wrkList);

        }

        model.addAttribute("resource`search", search);

        addNav(model, "resourceSearch");
        model.addAttribute("resources", returnList);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "projectSearch";
    }



}
