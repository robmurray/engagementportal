package com.ys.eportal.infra.repository;

import com.ys.eportal.infra.domain.ProjectEntity;
import com.ys.eportal.infra.domain.SalesOrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {

    List<ProjectEntity> findBySalesOrdersSalesOrderNumber(String salesOrderNumber);
    List<ProjectEntity> findByStatus(String status);
    List<ProjectEntity> findByNameLike(String customerName);
    List<ProjectEntity> findBySalesOrdersImportControlId(long importControlId);
    List<ProjectEntity> findBySalesOrdersModelGroup(String modelGroup);
}