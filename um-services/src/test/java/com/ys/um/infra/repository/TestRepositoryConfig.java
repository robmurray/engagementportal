package com.ys.um.infra.repository;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories(basePackages = "com.ys.um.infra.repository")
@EntityScan(basePackages = "com.ys.um.infra.domain")
@Import({ DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class TestRepositoryConfig {}