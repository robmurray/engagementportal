package com.ys.eportal.infra.repository;

import com.ys.eportal.infra.domain.ProjectActivityEntity;
import com.ys.eportal.infra.domain.ProjectNotesEntity;
import org.springframework.data.repository.CrudRepository;


public interface ProjectNotesRepository extends CrudRepository<ProjectNotesEntity, Long> {



}