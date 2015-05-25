package com.ys.eportal.repository.user;

import com.ys.core.infra.repository.user.UserRepository;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories(basePackages = "com.ys.core.infra.repository.user")
@EntityScan(basePackages = "com.ys.core.infra.domain.user")
@Import({ DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class TestRepositoryConfig {}