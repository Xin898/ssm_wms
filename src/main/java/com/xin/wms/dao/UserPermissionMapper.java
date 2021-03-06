package com.xin.wms.dao;

import com.xin.wms.pojo.RoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserPermissionMapper {

    /**
     * 为指定 userID 用户指派指定 roleID 的角色
     * @param userID 用户ID
     * @param roleID 角色ID
     */
    void insert(@Param("userID") Integer userID, @Param("roleID") Integer roleID);

    /**
     * 删除指定用户的角色
     * @param userID 用户ID
     */
    void deleteByUserID(Integer userID);

    /**
     * 获取指定 userID 对应用户拥有的角色
     * @param userID 用户ID
     * @return 返回 userID 指定用户的角色
     */
    List<RoleDO> selectUserRole(Integer userID);

}
