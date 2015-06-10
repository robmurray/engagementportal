package com.ys.em.mapper;

import com.ys.em.infra.domain.CustomerEntity;
import com.ys.em.infra.domain.ProjectExportEntity;
import com.ys.em.infra.domain.ProjectSearchSupport;
import com.ys.em.model.Customer;
import com.ys.em.model.ProjectExport;
import com.ys.em.model.ProjectSearch;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by rob on 4/9/15.
 */
@Component
public class ProjectExportMapper {

    private MapperFactory mapperFactory;

    public ProjectExportMapper() {
        this.register();
    }

    private void register(){
        mapperFactory = new DefaultMapperFactory.Builder().build();
        // map to/from
        mapperFactory.classMap(ProjectExportEntity.class, ProjectExport.class)
                .field("name","customerName")
                .byDefault()
                .register();

        // map from/to
        mapperFactory.classMap(ProjectExport.class,ProjectExportEntity.class)
                .field("customerName","name")
                .byDefault()
                .register();

    }

    public ProjectExport convert(ProjectExportEntity projectExportEntity) {
        ProjectExport projectExport = null;

        if(projectExportEntity== null){
            return null;
        }

        MapperFacade mapper = mapperFactory.getMapperFacade();
        projectExport = mapper.map(projectExportEntity, ProjectExport.class);
        return projectExport;
    }

    public ProjectExportEntity convert(ProjectExport projectExport) {
        ProjectExportEntity projectExportEntity = null;

        if(projectExport== null){
            return null;
        }

        MapperFacade mapper = mapperFactory.getMapperFacade();
        projectExportEntity = mapper.map(projectExport, ProjectExportEntity.class);

        return projectExportEntity;
    }

    public Iterable<ProjectExport> convert(Iterable<ProjectExportEntity> projectExportEntities){
        if(projectExportEntities== null){
            return null;
        }

        Collection<ProjectExport> projectExports = new HashSet<ProjectExport>();
        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.mapAsCollection(projectExportEntities, projectExports, ProjectExport.class);

        return projectExports;

    }

}
