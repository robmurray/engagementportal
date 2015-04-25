package com.ys.eportal.service.converter;

import com.ys.eportal.infra.domain.CustomerEntity;
import com.ys.eportal.infra.domain.ImportMasterEntity;
import com.ys.eportal.infra.domain.SalesOrderEntity;
import com.ys.eportal.model.Customer;
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
public class SOImportMapper {

    private MapperFactory mapperFactory;

    public SOImportMapper() {
        this.register();
    }

    private void register(){
        mapperFactory = new DefaultMapperFactory.Builder().build();
        // map to/from
        mapperFactory.classMap(ImportMasterEntity.class, SalesOrderEntity.class)
                .field("salesOrderNumber", "salesOrderNumber")
       /*         .field("classRegSent", "classRegSent")
                .field("reportedRevRec", "reportedRevRec")
                .field("customer", "customer")
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
          */      .byDefault()
                .register();

        // map from/to
        mapperFactory.classMap(ImportMasterEntity.class,SalesOrderEntity.class)
                .byDefault()
                .register();

    }


    public SalesOrderEntity convert(ImportMasterEntity importMasterEntity) {
        SalesOrderEntity salesOrderEntity=null;

        if(importMasterEntity== null){
            return null;
        }

        MapperFacade mapper = mapperFactory.getMapperFacade();
        salesOrderEntity = mapper.map(importMasterEntity, SalesOrderEntity.class);
        return salesOrderEntity;
    }


    public Iterable<SalesOrderEntity> convert(Iterable<ImportMasterEntity> importMasterEntities){
        if(importMasterEntities== null){
            return null;
        }

        Collection<SalesOrderEntity> salesOrderEntities = new HashSet<SalesOrderEntity>();
        MapperFacade mapper = mapperFactory.getMapperFacade();

       mapper.mapAsCollection(importMasterEntities, salesOrderEntities, SalesOrderEntity.class);

        return salesOrderEntities;

    }
}
