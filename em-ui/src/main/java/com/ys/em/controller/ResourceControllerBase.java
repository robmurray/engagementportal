package com.ys.em.controller;


import com.ys.em.infra.domain.ResourceEntity;
import com.ys.em.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rob on 4/4/15.
 */
@Controller
public class ResourceControllerBase extends EportalBaseController {
    private static Logger logger = LoggerFactory.getLogger(ResourceControllerBase.class);

    protected List<Resource> load(Iterable<ResourceEntity> paeList) {

        List<Resource> returnList = new ArrayList<Resource>();
        for(ResourceEntity re:paeList){
            returnList.add(this.load(re));
        }
        return returnList;
    }
    protected Resource load(ResourceEntity pae) {
        Resource r = null;
        if (pae != null) {
            r = new Resource();
            r.setResourceId(pae.getResourceId());
            r.setFirstName(pae.getFirstName());
            r.setLastName(pae.getLastName());
            r.setResourceId(pae.getResourceId());
            r.setType(pae.getType());
        }
        return r;
    }

}
