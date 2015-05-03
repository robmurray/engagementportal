package com.ys.eportal.infra.repository;

import com.ys.eportal.infra.domain.ProjectResourceEntity;
import com.ys.eportal.infra.domain.ResourceEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


public interface ProjectResourceRepository extends CrudRepository<ProjectResourceEntity, Long> {



    @Modifying
    @Transactional
    @Query("delete from ProjectResourceEntity pre where pre.projectResourceId = ?1")
    public void deleteProjectResourceEntity(long projectResourceEntityId);




}