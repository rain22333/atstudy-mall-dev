package com.atstudy.mapper;

import com.atstudy.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 访问数据库中 Role 数据表的 数据访问层接口
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {


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
