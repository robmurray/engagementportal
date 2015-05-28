package com.ys.um.mapper;

import com.ys.um.infra.domain.RoleEntity;
import com.ys.um.infra.domain.UserEntity;
import com.ys.um.model.Role;
import com.ys.um.model.User;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by rob on 4/9/15.
 */
@Component
public class RoleMapper {

    private MapperFactory mapperFactory;

    public RoleMapper() {
        this.register();
    }

    private void register() {
        mapperFactory = new DefaultMapperFactory.Builder().build();
        // map to/from
        mapperFactory.classMap(UserEntity.class, User.class)
                .byDefault()
                .register();

        // map from/to
        mapperFactory.classMap(User.class, UserEntity.class)
                .byDefault()
                .register();

    }

    public Role convert(RoleEntity roleEntity) {
        Role role = null;

        if (roleEntity == null) {
            return null;
        }

        MapperFacade mapper = mapperFactory.getMapperFacade();
        role = mapper.map(roleEntity, Role.class);
        return role;
    }

    public RoleEntity convert(Role role) {
        RoleEntity roleEntity = null;

        if (role == null) {
            return null;
        }

        MapperFacade mapper = mapperFactory.getMapperFacade();
        roleEntity = mapper.map(role, RoleEntity.class);

        return roleEntity;
    }


    public Iterable<Role> convert(Iterable<RoleEntity> roleEntities) {
        if (roleEntities == null) {
            return null;
        }

        Collection<Role> roles = new HashSet<Role>();
        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.mapAsCollection(roleEntities, roles, Role.class);

        return roles;

    }
}
