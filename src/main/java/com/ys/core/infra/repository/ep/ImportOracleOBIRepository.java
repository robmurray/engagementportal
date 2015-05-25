package com.ys.core.infra.repository.ep;

import com.ys.core.infra.domain.ep.ImportOracleObiEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ImportOracleOBIRepository extends CrudRepository<ImportOracleObiEntity, Long> {

    public List<ImportOracleObiEntity> findByImportControlId(long importControlId);

}