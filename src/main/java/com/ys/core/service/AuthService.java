package com.ys.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by rob on 4/4/15.
 */

@Service
@Transactional
public class AuthService extends ServicesBase {

    private static Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserService userService;

    @PersistenceContext
    private EntityManager entityManager;



    public void login(String userName, String password){

    }

    public void logoff(){

    }

    public void changePassword(String newPassword, String newPasswordConfirm,String oldPassword){

    }

    public void triggerPasswordReset(String emailAddress){

    }


}