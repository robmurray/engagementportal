package com.ys.core.service;

import com.ys.core.infra.domain.user.RoleEntity;
import com.ys.core.infra.domain.user.UserEntity;
import com.ys.core.infra.repository.user.RoleRepository;
import com.ys.core.infra.repository.user.UserRepository;
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
public class UserService extends ServicesBase {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PersistenceContext
    private EntityManager entityManager;


    private void saveUser(UserEntity ue){

        this.userRepository.save(ue);

    }


    public UserEntity findUserByEmail(String email){

        // valiodation

        return this.findUserByEmail(email);

    }

    public Iterable<UserEntity> findAllUsers(){
        return this.userRepository.findAll();
    }


    public UserEntity findUserById(long userId){
        return this.userRepository.findOne(userId);
    }


    public Iterable<RoleEntity> findAllRoles(){
        return this.roleRepository.findAll();
    }

    public RoleEntity findRoleById(long roleId){
        return this.roleRepository.findOne(roleId);
    }

    public void save(UserEntity ue){
        this.userRepository.save(ue);
    }

    public void save(RoleEntity re){
        this.roleRepository.save(re);
    }


    public void updatePassword(UserEntity ue, String newPassword){
        this.userRepository.updatePassword(newPassword,ue.getUserId());

    }
}