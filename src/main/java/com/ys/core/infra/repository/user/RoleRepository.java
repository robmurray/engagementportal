package com.ys.core.infra.repository.user;

import com.ys.core.infra.domain.user.RoleEntity;
import com.ys.core.infra.domain.user.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RoleRepository extends CrudRepository<RoleEntity, Long> {


}