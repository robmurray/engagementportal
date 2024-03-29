package com.ys.em.mapper;

import com.ys.em.infra.domain.CustomerSearchSupport;
import com.ys.em.model.CustomerSearch;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

/**
 * Created by rob on 4/9/15.
 */
@Component
public class CustomerSearchMapper {

    private MapperFactory mapperFactory;

    public CustomerSearchMapper() {
        this.register();
    }

    private void register(){
        mapperFactory = new DefaultMapperFactory.Builder().build();
        // map to/from
        mapperFactory.classMap(CustomerSearchSupport.class, CustomerSearch.class)
                .field("customerId", "customerId")
                .field("name", "name")
                .byDefault()
                .register();

        // map from/to
        mapperFactory.classMap(CustomerSearch.class, CustomerSearchSupport.class)
                .field("customerId", "customerId")
                .field("name", "name")
                .byDefault()
                .register();

    }

    public CustomerSearch convert(CustomerSearchSupport customerSearchSupport) {
        CustomerSearch customerSearch = null;

        if(customerSearchSupport== null){
            return null;
        }

        MapperFacade mapper = mapperFactory.getMapperFacade();
        customerSearch = mapper.map(customerSearchSupport, CustomerSearch.class);
        return customerSearch;
    }

    public CustomerSearchSupport convert(CustomerSearch customerSearch) {
        CustomerSearchSupport customerSearchSupport = null;

        if(customerSearch== null){
            return null;
        }

        MapperFacade mapper = mapperFactory.getMapperFacade();
        customerSearchSupport = mapper.map(customerSearch, CustomerSearchSupport.class);

        return customerSearchSupport;
    }

}
