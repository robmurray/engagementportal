package com.ys.eportal.controller;

import com.ys.core.infra.domain.ep.ProjectEntity;
import com.ys.eportal.mapper.ProjectSearchMapper;
import com.ys.eportal.model.ProjectSearch;
import com.ys.eportal.model.ProjectSearchResults;
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

        Iterable<ProjectEntity> wrkList = this.portalService.findAllProjectsSearchResults();

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

        Iterable<ProjectEntity> wrkList = this.portalService.find(this.projectSearchMapper.convert(search));

        List<ProjectSearchResults> returnList = buildSearchResults(wrkList);


        addNav(model,"projectSearch");
        model.addAttribute("projects", returnList);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "projectSearch";
    }


    private List<ProjectSearchResults> buildSearchResults(Iterable<ProjectEntity> list) {


        // @TODO test autnorization
        boolean readonly = false;
        List<ProjectSearchResults> returnList = null;
        if (list != null) {
            returnList = new ArrayList<ProjectSearchResults>();
            ProjectSearchResults ps = null;
            for (ProjectEntity pe : list) {

                if (pe == null) {
                    // won't happen
                    logger.error("project record null wtf ");
                    continue;
                }
                if (pe.getSalesOrders() == null || pe.getSalesOrders().getCustomer() == null) {
                    // bad record log and skip
                    logger.error("bad project record: " + pe.getProjectId());
                    continue;

                }

                ps = new ProjectSearchResults(pe.getProjectId(), pe.getSalesOrders().getSalesOrderId(), pe.getSalesOrders().getCustomer().getCustomerId(), pe.getSalesOrders().getSalesOrderNumber(), pe.getSalesOrders().getCustomer().getName(), pe.getStatus(),pe.getHealth());
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
