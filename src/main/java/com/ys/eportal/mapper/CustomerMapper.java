package com.ys.eportal.mapper;

import com.ys.eportal.infra.domain.CustomerEntity;
import com.ys.eportal.model.Customer;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

/**
 * Created by rob on 4/9/15.
 */
@Component
public class CustomerMapper {

    private MapperFactory mapperFactory;

    public CustomerMapper() {
        this.register();
    }

    private void register(){
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        // map to/from
        mapperFactory.classMap(CustomerEntity.class, Customer.class)
                .field("customerId", "customerId")
                .field("name", "name")
                .field("credits", "credits")
                .field("contact", "contact")
                .field("createDate", "createDate")
                .field("modifiedDate", "modifiedDate")
                .byDefault()
                .register();

        // map from/to
        mapperFactory.classMap(Customer.class, CustomerEntity.class)
                .field("customerId", "customerId")
                .field("name", "name")
                .field("credits", "credits")
                .field("contact", "contact")
                .field("createDate", "createDate")
                .field("modifiedDate", "modifiedDate")
                .byDefault()
                .register();

    }

    public Customer convert(CustomerEntity customerEntity) {
        Customer customer = null;

        if(customerEntity== null){
            return null;
        }

        MapperFacade mapper = mapperFactory.getMapperFacade();
        customer = mapper.map(customerEntity, Customer.class);
        return customer;
    }

    public CustomerEntity convert(Customer customer) {
        CustomerEntity customerEntity = null;

        if(customer== null){
            return null;
        }

        MapperFacade mapper = mapperFactory.getMapperFacade();
        customer = mapper.map(customerEntity, Customer.class);

        return customerEntity;
    }
}
