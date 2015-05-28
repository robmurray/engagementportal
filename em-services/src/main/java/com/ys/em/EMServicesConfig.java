package com.ys.em;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// the current release version of spring boot has a bug
// multiple @entity scans are ignored
// for now moving the @Entity scan to the application.class
@Configuration
@EnableJpaRepositories(basePackages = {"com.ys.em.infra.repository"})
//@EntityScan(basePackages = {"com.ys.em.infra.domain"})
public class EMServicesConfig {}