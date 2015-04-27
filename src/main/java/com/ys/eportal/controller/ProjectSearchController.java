package com.ys.eportal.controller;

import com.ys.eportal.infra.domain.ProjectEntity;

import com.ys.eportal.mapper.ProjectSearchMapper;
import com.ys.eportal.model.*;
import com.ys.eportal.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class ProjectSearchController  extends ControllerBase{

    @Autowired
    private PortalService portalService;


    @Autowired
    private ProjectSearchMapper projectSearchMapper;

    @RequestMapping(value="/projectbatchid", method= RequestMethod.GET)
    public String projectSearchForm3(@RequestParam(value="batchid", required=true) long batchId, Model model) {
        ProjectSearch search = new ProjectSearch();
        search.setImportControlId(batchId);
        return this.submitSearchForm(search, model);
    }
    @RequestMapping(value="/projectstatus", method= RequestMethod.GET)
    public String projectSearchForm2(@RequestParam(value="status", required=true) String status, Model model) {
        ProjectSearch search = new ProjectSearch();
        search.setStatus(status);
        return this.submitSearchForm(search, model);
    }

    @RequestMapping(value="/projectSearch", method= RequestMethod.GET)
    public String projectSearchForm(Model model) {

        Iterable<ProjectEntity> wrkList = this.portalService.findAllProjects();
        List<ProjectSearchResults> returnList=buildSearchResults(wrkList);


        model.addAttribute("pageName", "Project Search");
        model.addAttribute("projectsearch", new ProjectSearch());
        model.addAttribute("projects", returnList);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "projectSearch";
    }


    @RequestMapping(value="/projectSearch", method=RequestMethod.POST)
    public String submitSearchForm(@ModelAttribute ProjectSearch search, Model model) {
        model.addAttribute("pageName", "Project Search");
        model.addAttribute("projectsearch", search);

        Iterable<ProjectEntity> wrkList = this.portalService.find(this.projectSearchMapper.convert(search));

        List<ProjectSearchResults> returnList=buildSearchResults(wrkList);


        model.addAttribute("projects", returnList);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "projectSearch";
    }


    private List<ProjectSearchResults> buildSearchResults(Iterable<ProjectEntity> list){

        List<ProjectSearchResults> returnList= null;
        if(list !=null){
            returnList= new ArrayList<ProjectSearchResults>();
            ProjectSearchResults ps = null;
            for(ProjectEntity pe:list){
                ps = new ProjectSearchResults(pe.getProjectId(),pe.getSalesOrders().getSalesOrderNumber(),pe.getSalesOrders().getCustomer().getName(),pe.getStatus());
                returnList.add(ps);

            }

        }
        if(returnList.size()<1){
            returnList=null;
        }
        return returnList;
    }
}
