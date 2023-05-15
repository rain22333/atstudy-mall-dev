package com.atstudy.service.impl;

import com.atstudy.entity.Role;
import com.atstudy.entity.RolePermission;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.mapper.RoleMapper;
import com.atstudy.mapper.RolePermissionMapper;
import com.atstudy.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色表 业务逻辑层 实现类
 */
@Service
public class RoleServiceImpl implements RoleService{



    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;


    @Transactional
    @Override
    public boolean deleteById(Integer id) {

        // 先删除角色表的数据
        Map<String,Object> map = new HashMap<>();
        map.put("role_id",id);
        int i1 = roleMapper.deleteByMap(map);
        // 再删除角色-权限关系表的数据
        int i2 = rolePermissionMapper.deleteByMap(map);

        return i1 >= 1 && i2 >= 1;
    }

    @Transactional
    @Override
    public boolean batchDeleteByIdS(Integer[] idList) {

        // 批量删除角色表中的数据
        int i1 = roleMapper.deleteByRoleIdList(idList);
        // 批量删除角色权限关系表的数据
        int i2 = rolePermissionMapper.deleteByRoleIdList(idList);
        return i1 >= 1 && i2 >= 1;
    }

    @Transactional
    @Override
    public boolean updateOne(Integer roleId, String roleName,Integer[] idList) {
        Role role = new Role();
        role.setRoleId(roleId);
        role.setRoleName(roleName);

        UpdateWrapper<Role> wrapper = new UpdateWrapper<>();
        wrapper.set("role_name",roleName)
                        .eq("role_id",roleId);

        // 更新角色表中的数据
        int i1 = roleMapper.update(role, wrapper);

        // 将原有的角色、权限关系删除，再重新添加
        Map<String,Object> map = new HashMap<>();
        map.put("role_id",roleId);
        int i2 = rolePermissionMapper.deleteByMap(map);
        // 重新·添加角色-权限关系
        int i3 = roleMapper.batchInsertRolePermission(role, idList);

        return i1 >= 1 && i2 >= 1 && i3 >= 1;
    }

    @Override
    public Role getById(Integer roleId) {

        // 创建条件构造器
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",roleId);
        Role role = roleMapper.selectOne(wrapper);

        return role;
    }

    @Transactional
    @Override
    public boolean insert(String roleName, Integer[] idList) {

        List<Role> list = roleMapper.selectList(new QueryWrapper<Role>());
        Role role = new Role();
        role.setRoleId(list.size() + 1);
        role.setRoleName(roleName);

        // 插入角色
        int i1 = roleMapper.insert(role);

        // 插入角色-权限关系
        int i2 = roleMapper.batchInsertRolePermission(role, idList);

        return i1 >= 1 && i2 >= 1;
    }

    @Override
    public boolean isNameExists(String name) {

        // 创建条件构造器
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name",name);
        Role role = roleMapper.selectOne(wrapper);


        // 如果角色不是null，返回false表示不存在
        return role == null ? false : true;
    }

    @Override
    public List<Role> list() {
        return roleMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<Role> listByRoleAndPageBo(Role role, PageBo pageBo) {

        // 先查询出满足条件的总记录数
        int count = roleMapper.getCount(role);

        // 给pageBo的总记录数赋值
        pageBo.setResultCount((long) count);

        // 查询出满足条件的角色列表
        List<Role> list = roleMapper.listByRoleAndPageBO(role, pageBo);

        return list;
    }
}
