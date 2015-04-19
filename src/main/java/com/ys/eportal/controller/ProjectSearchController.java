package com.ys.eportal.controller;

import com.ys.eportal.infra.domain.CustomerEntity;
import com.ys.eportal.infra.domain.SalesOrderEntity;
import com.ys.eportal.mapper.CustomerMapper;
import com.ys.eportal.mapper.CustomerSearchMapper;
import com.ys.eportal.mapper.ProjectMapper;
import com.ys.eportal.mapper.ProjectSearchMapper;
import com.ys.eportal.model.Customer;
import com.ys.eportal.model.CustomerSearch;
import com.ys.eportal.model.Project;
import com.ys.eportal.model.ProjectSearch;
import com.ys.eportal.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class ProjectSearchController {

    @Autowired
    private PortalService portalService;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectSearchMapper projectSearchMapper;


    @RequestMapping(value="/projectSearch", method= RequestMethod.GET)
    public String projectSearchForm(Model model) {

        model.addAttribute("pageName", "Project Search");
        model.addAttribute("projectsearch", new ProjectSearch());
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "projectSearch";
    }

    @RequestMapping(value="/projectSearch", method=RequestMethod.POST)
    public String customerSubmit(@ModelAttribute ProjectSearch search, Model model) {
        model.addAttribute("pageName", "Project Search");
        model.addAttribute("projectsearch", search);

        Iterable<SalesOrderEntity> wrkList = this.portalService.find(this.projectSearchMapper.convert(search));


        Iterable<Project> returnList = this.projectMapper.convert(wrkList);
        if(returnList !=null && !returnList.iterator().hasNext()){
            returnList =null;
        }
        model.addAttribute("projects", returnList);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "projectSearchResults";
    }

}
