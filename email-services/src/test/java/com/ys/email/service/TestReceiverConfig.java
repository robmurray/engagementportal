package com.ys.email.service;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by rob on 5/29/15.
 */
@Configuration
@ImportResource({"classpath:META-INF/spring/integration/soifluke-imap-idle-config.xml","classpath:config/datasource.xml"})
@ComponentScan({"com.ys.email.service","com.ys.um.service","com.ys.em.service"})
@EntityScan(basePackages = {"com.ys.em.infra.domain","com.ys.um.infra.domain"})
@EnableAutoConfiguration
public class TestReceiverConfig {


}
