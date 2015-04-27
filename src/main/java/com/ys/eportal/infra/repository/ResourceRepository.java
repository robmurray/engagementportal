package com.ys.eportal.infra.repository;

import com.ys.eportal.infra.domain.ResourceEntity;
import com.ys.eportal.infra.domain.SalesOrderEntity;
import org.springframework.data.repository.CrudRepository;


public interface ResourceRepository extends CrudRepository<ResourceEntity, Long> {



}