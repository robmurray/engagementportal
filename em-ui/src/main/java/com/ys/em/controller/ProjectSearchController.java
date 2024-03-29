package com.ys.em.controller;

import com.ys.em.infra.domain.UMProjectSearchResults;
import com.ys.em.mapper.ProjectSearchMapper;
import com.ys.em.model.ProjectSearch;
import com.ys.em.model.ProjectSearchResults;
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
public class ProjectSearchController extends EportalBaseController {
    private static Logger logger = LoggerFactory.getLogger(ProjectSearchController.class);

    @Autowired
    private ProjectSearchMapper projectSearchMapper;

    @RequestMapping(value = "/projectbatchid", method = RequestMethod.GET)
    public String projectSearchForm3(@RequestParam(value = "batchid", required = true) long batchId, Model model) {
        ProjectSearch search = new ProjectSearch();
        search.setImportControlId(batchId);
        return this.submitSearchForm(search, model);
    }

    @RequestMapping(value = "/projectstatus", method = RequestMethod.GET)
    public String projectSearchForm2(@RequestParam(value = "status", required = true) String status, Model model) {
        ProjectSearch search = new ProjectSearch();
        search.setStatus(status);
        return this.submitSearchForm(search, model);
    }

    @RequestMapping(value = "/projectSearch", method = RequestMethod.GET)
    public String projectSearchForm(Model model) {

        Iterable<UMProjectSearchResults> wrkList = this.portalService.findAllProjectsSearchResults();

        List<ProjectSearchResults> returnList = buildSearchResults(wrkList);

        // @TODO check aauthorization
        ProjectSearch project =new ProjectSearch();

        addNav(model,"projectSearch");
        model.addAttribute("pageName", "Project Search");
        model.addAttribute("projectsearch", project);
        model.addAttribute("projects", returnList);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "projectSearch";
    }


    @RequestMapping(value = "/projectSearch", method = RequestMethod.POST)
    public String submitSearchForm(@ModelAttribute ProjectSearch search, Model model) {
        model.addAttribute("pageName", "Project Search");
        model.addAttribute("projectsearch", search);

//        Iterable<ProjectEntity> wrkList = null;//this.portalService.find(this.projectSearchMapper.convert(search));
        Iterable<UMProjectSearchResults> wrkList = this.portalService.findAllProjectsSearchResults();

        List<ProjectSearchResults> returnList = buildSearchResults(wrkList);


        addNav(model,"projectSearch");
        model.addAttribute("projects", returnList);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "projectSearch";
    }


    private List<ProjectSearchResults> buildSearchResults(Iterable<UMProjectSearchResults> list) {


        // @TODO test autnorization
        boolean readonly = false;
        List<ProjectSearchResults> returnList = null;
        if (list != null) {
            returnList = new ArrayList<ProjectSearchResults>();
            ProjectSearchResults ps = null;
            for (UMProjectSearchResults pe : list) {

                if (pe == null) {
                    // won't happen
                    logger.error("project record null wtf ");
                    continue;
                }

                ps = new ProjectSearchResults(pe.getProjectId(), pe.getSalesOrderId(),
                        pe.getCustomerId(), pe.getSalesOrderNumber(), pe.getCustomerName(),
                        pe.getModelGroup(),pe.getBookDate(),pe.getStatus(),pe.getHealth());



                ps.setReadonly(readonly);
                returnList.add(ps);

            }

            if (returnList.size() < 1) {
                returnList = null;
            }
        }

        return returnList;
    }
}
