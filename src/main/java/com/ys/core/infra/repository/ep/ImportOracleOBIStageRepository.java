package com.ys.core.infra.repository.ep;

import com.ys.core.infra.domain.ep.ImportOracleObiStage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ImportOracleOBIStageRepository extends CrudRepository<ImportOracleObiStage, Long> {

    public List<ImportOracleObiStage> findByImportControlId(long importControlId);

}