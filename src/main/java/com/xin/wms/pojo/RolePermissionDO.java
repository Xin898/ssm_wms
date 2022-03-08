package com.xin.wms.pojo;

import java.io.Serializable;

//角色权限
public class RolePermissionDO implements Serializable {

    //URL 的角色角色权限信息名称
    private String name;

    //URL 的角色角色权限信息对应的 URL
    private String url;

    //URL 的角色角色权限信息对应的角色
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RolePermissionDO{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
