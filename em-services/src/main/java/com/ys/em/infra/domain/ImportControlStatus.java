package com.ys.em.infra.domain;

/**
 * Created by rob on 4/16/15.
 */
public enum ImportControlStatus {
    BEGIN,
    CSV2STAGING_START,
    CSV2STAGING_COMPLETE,
    STAGING2IMPORT_START,
    STAGING2IMPORT_COMPLETE,
    IMPORT2SO_START,
    IMPORTSO_COMPLETE,
    COMPLETE,
    NOTHING2DO,
    NOMATCHINGMODELGROUP,
    ERROR

}
