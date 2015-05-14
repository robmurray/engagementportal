package com.ys.eportal.infra.repository;

import com.ys.eportal.infra.domain.ProjectActivityEntity;
import com.ys.eportal.infra.domain.ProjectNotesEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


public interface ProjectNotesRepository extends CrudRepository<ProjectNotesEntity, Long> {


    @Modifying
    @Transactional
    @Query("delete from ProjectNotesEntity prn where prn.noteId = ?1")
    public void deleteProjectNotesEntity(long noteId);



}