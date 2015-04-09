package com.ys.eportal.infra.repository;

import com.ys.eportal.infra.domain.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {



}