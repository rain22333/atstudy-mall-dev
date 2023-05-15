package com.atstudy.service;

import com.atstudy.entity.Role;
import com.atstudy.entity.bo.PageBo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色表 业务逻辑层 接口
 */
public interface RoleService{


    /**
     * 根据角色id删除一个角色
     * @param id
     * @return
     */
    boolean deleteById(Integer id);

    /**
     * 根据角色id数组批量删除角色
     * @param idList
     * @return
     */
    boolean batchDeleteByIdS(Integer[] idList);


    /**
     * 更新一个角色
     * @param roleId
     * @param roleName
     * @param isList
     * @return
     */
    boolean updateOne(Integer roleId,String roleName,Integer[] isList);

    /**
     * 根据角色编号查询角色对象
     * @param roleId
     * @return
     */
    Role getById(Integer roleId);


    /**
     * 添加角色
     * @param roleName  角色名称
     * @param idList    权限id数组
     * @return
     */
    boolean insert(String roleName,Integer[] idList);


    /**
     * 验证这个角色名是否存在
     * @param name
     * @return
     */
    boolean isNameExists(String name);

    /**
     * 查询出所有角色
     * @return
     */
    List<Role> list();


    /**
     * 根据前端页面传递过来的role和pageBo查询出满足条件的角色列表
     * @param role
     * @param pageBo
     * @return
     */
    List<Role> listByRoleAndPageBo(Role role, PageBo pageBo);


}
