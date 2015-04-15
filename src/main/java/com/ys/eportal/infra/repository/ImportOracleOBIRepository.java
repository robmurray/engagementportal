package com.ys.eportal.infra.repository;

import com.ys.eportal.infra.domain.ImportOracleObiEntity;
import com.ys.eportal.infra.domain.ImportOracleObiStage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ImportOracleOBIRepository extends CrudRepository<ImportOracleObiEntity, Long> {

    public List<ImportOracleObiEntity> findByImportControlId(long importControlId);

}