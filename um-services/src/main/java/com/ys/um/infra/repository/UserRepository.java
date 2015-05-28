package com.ys.um.infra.repository;

import com.ys.um.infra.domain.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;


public interface UserRepository extends CrudRepository<UserEntity, Long> {

    public List<UserEntity> findByFirstName(String firstName);
    public List<UserEntity> findByFirstNameLike(String lastName);

    public List<UserEntity> findByLastName(String firstName);
    public List<UserEntity> findByLastNameLike(String lastName);

    public List<UserEntity> findByEmail(String email);
    public List<UserEntity> findByEmailLike(String email);


    @Modifying
    @Transactional
    @Query("update UserEntity ue set password=?1 where ue.userId = ?2")
    public void updatePassword(String newPassword, long userId);


}