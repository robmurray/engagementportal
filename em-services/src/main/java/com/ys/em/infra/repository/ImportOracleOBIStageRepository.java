package com.ys.em.infra.repository;

import com.ys.em.infra.domain.ImportOracleObiStage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ImportOracleOBIStageRepository extends CrudRepository<ImportOracleObiStage, Long> {

    public List<ImportOracleObiStage> findByImportControlId(long importControlId);

}