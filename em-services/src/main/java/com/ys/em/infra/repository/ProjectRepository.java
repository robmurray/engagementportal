package com.ys.em.infra.repository;

import com.ys.em.infra.domain.ProjectEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {

    List<ProjectEntity> findBySalesOrdersSalesOrderNumber(String salesOrderNumber);
    List<ProjectEntity> findByStatus(String status);
    List<ProjectEntity> findByNameLike(String customerName);
    List<ProjectEntity> findBySalesOrdersImportControlId(long importControlId);
    List<ProjectEntity> findBySalesOrdersModelGroup(String modelGroup);
}