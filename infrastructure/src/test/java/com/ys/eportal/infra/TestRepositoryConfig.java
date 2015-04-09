package com.ys.eportal.infra;

import com.ys.eportal.infra.domain.Customer;
import com.ys.eportal.infra.repository.CustomerRepository;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = CustomerRepository.class)
@EntityScan(basePackages = "com.ys.eportal.infra.domain")
@Import({ DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class TestRepositoryConfig {}