package com.ys.eportal.infra.repository;

import com.ys.eportal.infra.domain.SalesOrderEntity;
import com.ys.eportal.infra.domain.SalesOrderEntityId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SalesOrderRepository extends CrudRepository<SalesOrderEntity, SalesOrderEntityId> {




}