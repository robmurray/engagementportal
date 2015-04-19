package com.ys.eportal.mapper;

import com.ys.eportal.infra.domain.CustomerEntity;
import com.ys.eportal.infra.domain.SalesOrderEntity;
import com.ys.eportal.model.Customer;
import com.ys.eportal.model.Project;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by rob on 4/9/15.
 */
@Component
public class ProjectMapper {

    private MapperFactory mapperFactory;

    public ProjectMapper() {
        this.register();
    }

    private void register(){
        mapperFactory = new DefaultMapperFactory.Builder().build();
        // map to/from
        mapperFactory.classMap(SalesOrderEntity.class, Project.class)
                .field("salesOrderId", "salesOrderNumber")
                .field("customerId", "customer.customerId")
                .field("importControlId", "importControlId")
                .field("classRegSent", "classRegSent")
                .field("reportedRevRec", "reportedRevRec")
                .field("status", "status")
                .field("bookDate", "bookDate")
                .field("shipDate", "shipDate")
                .field("planningMeetingDate", "planningMeetingDate")
                .field("kickoffMeetingDate", "kickoffMeetingDate")
                .field("onSiteStartDate", "onSiteStartDate")
                .field("onSiteEndDate", "onSiteEndDate")
                .field("releaseForRevenueRecDate", "releaseForRevenueRecDate")
                .field("waitTime", "waitTime")
                .field("bookedToKickOff", "bookedToKickOff")
                .field("daysToClose", "daysToClose")
                .field("amount", "amount")
                .field("notes", "notes")
                .field("location", "location")
                .field("region", "region")
                .field("modelGroup", "modelGroup")
                .field("service", "service")
                .field("accountTeam", "accountTeam")
                .field("remote", "remote")
                .field("onsite", "onsite")
                .byDefault()
                .register();

        // map from/to
        mapperFactory.classMap(Project.class, SalesOrderEntity.class)
                .field("salesOrderNumber", "salesOrderId")
                .field("customer.customerId", "customerId")
                .field("importControlId", "importControlId")
                .field("classRegSent", "classRegSent")
                .field("reportedRevRec", "reportedRevRec")
                .field("status", "status")
                .field("bookDate", "bookDate")
                .field("shipDate", "shipDate")
                .field("planningMeetingDate", "planningMeetingDate")
                .field("kickoffMeetingDate", "kickoffMeetingDate")
                .field("onSiteStartDate", "onSiteStartDate")
                .field("onSiteEndDate", "onSiteEndDate")
                .field("releaseForRevenueRecDate", "releaseForRevenueRecDate")
                .field("waitTime", "waitTime")
                .field("bookedToKickOff", "bookedToKickOff")
                .field("daysToClose", "daysToClose")
                .field("amount", "amount")
                .field("notes", "notes")
                .field("location", "location")
                .field("region", "region")
                .field("modelGroup", "modelGroup")
                .field("service", "service")
                .field("accountTeam", "accountTeam")
                .field("remote", "remote")
                .field("onsite", "onsite")
                .byDefault()
                .register();

    }

    public Project convert(SalesOrderEntity salesOrderEntity) {
        Project project = null;

        if(salesOrderEntity== null){
            return null;
        }

        MapperFacade mapper = mapperFactory.getMapperFacade();
        project = mapper.map(salesOrderEntity, Project.class);
        return project;
    }

    public SalesOrderEntity convert(Project project) {
        SalesOrderEntity salesOrderEntity = null;

        if(project== null){
            return null;
        }

        MapperFacade mapper = mapperFactory.getMapperFacade();
        salesOrderEntity= mapper.map(project, SalesOrderEntity.class);

        return salesOrderEntity;
    }


    public Iterable<Project> convert(Iterable<SalesOrderEntity> salesOrderEntities){
        if(salesOrderEntities== null){
            return null;
        }

        Collection<Project> projects = new HashSet<Project>();
        MapperFacade mapper = mapperFactory.getMapperFacade();

       mapper.mapAsCollection(salesOrderEntities, projects, Project.class);

        return projects;

    }
}
