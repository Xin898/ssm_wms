package com.xin.wms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

//角色映射器
@Mapper
@Component
public interface RolesMapper {

    /**
     * 获取角色对应的ID
     * @param roleName 角色名
     * @return 返回角色的ID
     */
    Integer getRoleID(String roleName);
}
