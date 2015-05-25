package com.ys.core.infra.repository.ep;

import com.ys.core.infra.domain.ep.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    public List<CustomerEntity> findByName(String name);
    public List<CustomerEntity> findByNameLike(String name);


}