package com.ys.em;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * Created by rob on 4/18/15.
 */
@Configuration
@EnableWebMvcSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringMVCConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    public static String encodePasswordWithBCrypt(String plainPassword) {
        return new BCryptPasswordEncoder().encode(plainPassword);
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select email,password, enabled from sec_user where email=?")
                .authoritiesByUsernameQuery(
                        "select u.email, r.name from sec_user u,sec_role r where u.role_id=r.role_id and u.email=?");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/assets/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    /*
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("flukeadmin").password("portal").roles("USER");
        auth
                .inMemoryAuthentication()
                .withUser("robmurray").password("portal").roles("ADMIN");


    }
    */
}