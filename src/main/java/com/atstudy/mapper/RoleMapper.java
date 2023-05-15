package com.atstudy.mapper;

import com.atstudy.entity.Role;
import com.atstudy.entity.bo.PageBo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * 访问数据库中 Role 数据表的 数据访问层接口
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {


    /**
     * 批量删除角色
     * @param idList
     * @return
     */
    int deleteByRoleIdList(@Param("idList") Integer[] idList);

    /**
     * 批量插入角色-权限关系
     * @param role
     * @param idList
     * @return
     */
    int batchInsertRolePermission(@Param("role") Role role,@Param("idList") Integer[] idList);


    /**
     * 根据Role和PageBo查询满足条件的角色列表
     * @param role
     * @param pageBo
     * @return
     */
    List<Role> listByRoleAndPageBO(@Param("role") Role role, @Param("pageBo") PageBo pageBo);

    /**
     * 查询满足条件的总记录数
     * @param role
     * @return
     */
    int getCount(@Param("role") Role role);


    /**
     * 根据用户id查询到这个用户所拥有的角色列表
     * @param adminId
     * @return
     */
    List<Role> getRoleListByAdminId(@Param("adminId") Integer adminId);


    /**
     * 根据url查询到角色列表
     * @return
     */
    List<Role> listByuUrl(@Param("url") String url);
}
