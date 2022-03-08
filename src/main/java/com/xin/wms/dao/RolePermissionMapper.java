package com.xin.wms.dao;

import com.xin.wms.pojo.RolePermissionDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

//角色权限映射器
@Mapper
@Component
public interface RolePermissionMapper {

    List<RolePermissionDO> selectAll();
}
