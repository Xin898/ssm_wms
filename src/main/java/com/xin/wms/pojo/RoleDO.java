package com.xin.wms.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleDO implements Serializable {

    private Integer id;// 角色ID
    private String roleName;// 角色名
    private String roleDesc;// 角色描述
    
}
