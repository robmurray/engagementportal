package com.ys.um.model;

import com.ys.core.infra.domain.AbstractDomainBase;
import com.ys.core.infra.domain.user.UserEntity;
import com.ys.ui.model.PageModelBase;

import javax.persistence.*;


public class Role extends PageModelBase {


    private long roleId;

    private String name;


    public Role() {
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
