package com.ys.em.infra.repository;

import com.ys.em.infra.domain.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    public List<CustomerEntity> findByName(String name);
    public List<CustomerEntity> findByNameLike(String name);


}