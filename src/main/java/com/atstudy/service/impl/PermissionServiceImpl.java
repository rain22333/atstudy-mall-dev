package com.atstudy.service.impl;

import com.atstudy.entity.Permission;
import com.atstudy.entity.vo.PermissionWithRoleVo;
import com.atstudy.mapper.PermissionMapper;
import com.atstudy.service.PermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限表 业务逻辑层实现类
 */
@Service
public class PermissionServiceImpl implements PermissionService {


    @Resource
    private PermissionMapper permissionMapper;


    @Override
    public List<Permission> list() {

        List<Permission> permissions = permissionMapper.selectList(new QueryWrapper<Permission>());


        return permissions;
    }

    @Override
    public List<PermissionWithRoleVo> listVoByRoleId(Integer roleId) {
        return permissionMapper.listVoWithRoleId(roleId);
    }

}
