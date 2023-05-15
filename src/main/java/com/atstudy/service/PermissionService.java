package com.atstudy.service;

import com.atstudy.entity.Permission;
import com.atstudy.entity.vo.PermissionWithRoleVo;

import java.util.List;

/**
 * 权限表  业务逻辑层
 */
public interface PermissionService {


    /**
     * 查询所有权限
     * @return
     */
    List<Permission> list();

    /**
     * 根据角色id查询PermissionWithRoleVO
     * @param roleId
     * @return
     */
    List<PermissionWithRoleVo> listVoByRoleId(Integer roleId);
}
