package com.xin.wms.pojo;

import lombok.Data;

import java.io.Serializable;

//角色权限
@Data
public class RolePermissionDO implements Serializable {

    //URL 的角色角色权限信息名称
    private String name;

    //URL 的角色角色权限信息对应的 URL
    private String url;

    //URL 的角色角色权限信息对应的角色
    private String role;

}
