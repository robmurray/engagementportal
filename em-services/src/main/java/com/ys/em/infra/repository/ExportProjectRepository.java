package com.ys.em.infra.repository;

import com.ys.em.infra.domain.ImportMasterEntity;
import com.ys.em.infra.domain.ProjectExportEntity;
import org.springframework.data.repository.CrudRepository;


public interface ExportProjectRepository extends CrudRepository<ProjectExportEntity, Long> {


}