package com.ys.eportal.infra.repository;

import com.ys.eportal.infra.domain.ImportMasterEntity;
import com.ys.eportal.infra.domain.ImportOracleObiEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ImportMasterRepository extends CrudRepository<ImportMasterEntity, Long> {


}