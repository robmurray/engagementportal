package com.ys.um.mapper;

import com.ys.um.infra.domain.UserEntity;
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
public class UserMapper {

    private MapperFactory mapperFactory;

    public UserMapper() {
        this.register();
    }

    private void register() {
        mapperFactory = new DefaultMapperFactory.Builder().build();
        // map to/from
        mapperFactory.classMap(UserEntity.class, User.class)
                .field("userId", "userId")
                .field("firstName", "firstName")
                .field("lastName", "lastName")
                .field("notes", "notes")
                .field("email", "email")
                .field("password", "password")
                .field("enabled", "enabled")
                .field("role", "role")
                .byDefault()
                .register();

        // map from/to
        mapperFactory.classMap(User.class, UserEntity.class)
                .field("userId", "userId")
                .field("firstName", "firstName")
                .field("lastName", "lastName")
                .field("notes", "notes")
                .field("email", "email")
                .field("password", "password")
                .field("enabled", "enabled")
                .field("role", "role")
                .byDefault()
                .register();

    }

    public User convert(UserEntity userEntity) {
        User user = null;

        if (userEntity == null) {
            return null;
        }

        MapperFacade mapper = mapperFactory.getMapperFacade();
        user = mapper.map(userEntity, User.class);
        return user;
    }

    public UserEntity convert(User user) {
        UserEntity userEntity = null;

        if (user == null) {
            return null;
        }

        MapperFacade mapper = mapperFactory.getMapperFacade();
        userEntity = mapper.map(user, UserEntity.class);

        return userEntity;
    }


    public Iterable<User> convert(Iterable<UserEntity> userEntities) {
        if (userEntities == null) {
            return null;
        }

        Collection<User> users = new HashSet<User>();
        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.mapAsCollection(userEntities, users, User.class);

        return users;

    }
}
