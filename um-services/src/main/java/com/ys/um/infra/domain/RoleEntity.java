package com.ys.um.infra.domain;

import com.ys.common.infra.domain.AbstractDomainBase;

import javax.persistence.*;

/**
 * Created by rob on 4/8/15.
 */
@Entity
@Table(name = "sec_Role")
public class RoleEntity extends AbstractDomainBase {

    @Id
    @Column(name = "roleId")
    private long roleId;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "name", unique = false)
    private String name;


    public RoleEntity() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleEntity)) return false;

        RoleEntity that = (RoleEntity) o;

        if (roleId != that.roleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (roleId ^ (roleId >>> 32));
    }
}
