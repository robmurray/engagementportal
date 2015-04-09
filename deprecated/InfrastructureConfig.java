/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ys.eportal.infra;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Common infrastructure configuration class to setup a Spring container and infrastructure components like a
 * {@link javax.sql.DataSource}, a {@link javax.persistence.EntityManagerFactory} and a {@link org.springframework.transaction.PlatformTransactionManager}. Will be used by the
 * configuration activating the plain JPA based repository configuration (see {@link PlainJpaConfig}) as well as the
 * Spring Data JPA based one (see {@link ApplicationConfig}).
 *
 * @author Oliver Gierke
 */
@Configuration
@EnableTransactionManagement
public class InfrastructureConfig {

	@Bean
	public DataSource dataSource() {
    return DataSourceBuilder.create().username("root")
                    .password("root123")
                    .url("jdbc:mysql://localhost:3306/engagementportal")
                    .driverClassName("com.mysql.jdbc.Driver")
                    .build();
	}

}
