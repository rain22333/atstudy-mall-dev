package com.atstudy.mapper;

import com.atstudy.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    /**
     * 根据角色id数组批量删除角色-权限关系
     * @param idList
     * @return
     */
    int deleteByRoleIdList(@Param("idList") Integer[] idList);
}
