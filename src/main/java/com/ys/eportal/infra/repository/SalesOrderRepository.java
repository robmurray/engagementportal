package com.ys.eportal.infra.repository;

import com.ys.eportal.infra.domain.SalesOrderEntity;
import com.ys.eportal.infra.domain.SalesOrderEntityId;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import java.util.List;


public interface SalesOrderRepository extends CrudRepository<SalesOrderEntity, SalesOrderEntityId> {

    public List<SalesOrderEntity> findBySalesOrderId(String salesOrderNumber);

    public List<SalesOrderEntity> findByimportControlId(long importControlId);
    public List<SalesOrderEntity> findByStatus(String status);

}