package com.ys.eportal.mapper;

import com.ys.eportal.infra.domain.CustomerSearchSupport;
import com.ys.eportal.infra.domain.ProjectSearchSupport;
import com.ys.eportal.model.CustomerSearch;
import com.ys.eportal.model.ProjectSearch;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

/**
 * Created by rob on 4/9/15.
 */
@Component
public class ProjectSearchMapper {

    private MapperFactory mapperFactory;

    public ProjectSearchMapper() {
        this.register();
    }

    private void register(){
        mapperFactory = new DefaultMapperFactory.Builder().build();
        // map to/from
        mapperFactory.classMap(ProjectSearchSupport.class, ProjectSearch.class)
                .field("salesOrderNumber", "salesOrderNumber")
                .field("customerName", "customerName")
                .field("importControlId", "importControlId")
                .field("status", "status")
                .byDefault()
                .register();

        // map from/to
        mapperFactory.classMap(ProjectSearch.class, ProjectSearchSupport.class)
                .field("salesOrderNumber", "salesOrderNumber")
                .field("customerName", "customerName")
                .field("importControlId", "importControlId")
                .field("status", "status")
                .byDefault()
                .register();

    }

    public ProjectSearch convert(ProjectSearchSupport projectSearchSupport) {
        ProjectSearch projectSearch = null;

        if(projectSearchSupport== null){
            return null;
        }

        MapperFacade mapper = mapperFactory.getMapperFacade();
        projectSearch = mapper.map(projectSearchSupport, ProjectSearch.class);
        return projectSearch;
    }

    public ProjectSearchSupport convert(ProjectSearch projectSearch) {
        ProjectSearchSupport projectSearchSupport = null;

        if(projectSearch== null){
            return null;
        }

        MapperFacade mapper = mapperFactory.getMapperFacade();
        projectSearchSupport = mapper.map(projectSearch, ProjectSearchSupport.class);

        return projectSearchSupport;
    }

}
