package com.ys.eportal.infra.repository;

import com.ys.eportal.infra.domain.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {

    public List<CustomerEntity> findByName(String name);


}