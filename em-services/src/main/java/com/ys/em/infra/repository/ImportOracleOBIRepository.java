package com.ys.em.infra.repository;

import com.ys.em.infra.domain.ImportOracleObiEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ImportOracleOBIRepository extends CrudRepository<ImportOracleObiEntity, Long> {

    public List<ImportOracleObiEntity> findByImportControlId(long importControlId);

}