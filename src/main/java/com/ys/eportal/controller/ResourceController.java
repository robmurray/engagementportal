package com.ys.eportal.controller;


import com.ys.eportal.infra.domain.ResourceEntity;
import com.ys.eportal.infra.repository.ResourceRepository;
import com.ys.eportal.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class ResourceController extends ResourceControllerBase {
    private static Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @RequestMapping(value = "/resource", method = RequestMethod.GET)
    public String resourceViewForm(@RequestParam(value = "resourceId", required = true) long resourceId,
                                   @RequestParam(value = "returnURL", required = false) String returnURL,
                                   @RequestParam(value = "anchor", required = false) String anchor,
                                   @RequestParam(value = "msgtype", required = false) String messageType, Model model) {

        ResourceEntity re = null;

        re = this.portalService.findResourceEntityById(resourceId);

        Resource r = null;

        if (re != null) {
            r = this.load(re);

        }
        addNav(model, returnURL);
        addPageAttributes(model, "Resource", "Resource");
        model.addAttribute("resource", r);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "resource";
    }

    @RequestMapping(value = "/resource", method = RequestMethod.POST)
    public String resourceSubmitForm(@ModelAttribute Resource resource, Model model) {

        // pull original
        ResourceEntity re = this.portalService.findResourceEntityById(resource.getResourceId());


        if (re != null) {
            re.setFirstName(resource.getFirstName());
            re.setLastName(resource.getLastName());
            re.setType(resource.getType());
            this.portalService.save(re);

            // repull
            re= this.portalService.findResourceEntityById(re.getResourceId());

            resource = this.load(re);
        }

//        this.setSuccessAlertMessage(model, "Resource updated");

        addNav(model, "resourceSearch");
        addPageAttributes(model, "Resource", "Resource");
        model.addAttribute("resource", resource);
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "resource";


    }

    @RequestMapping(value = "/resourceDelete", method = RequestMethod.GET)
    public String resourceDeleteSubmit(@RequestParam(value = "resourceId", required = true) long resourceId,
                                       @RequestParam(value = "returnURL", required = false) String returnURL,
                                       Model model){


        // pull original
        ResourceEntity re = this.portalService.findResourceEntityById(resourceId);


        if (re != null) {
            this.portalService.delete(re);
        }

        //this.setSuccessAlertMessage(model, "Resource deleted");


        addPageAttributes(model, "Resource", "Resource");
        model.addAttribute("pageGroup", "project");
        model.addAttribute("pageId", "searchProject");
        return "resource";


    }


}
