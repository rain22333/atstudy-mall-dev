package com.atstudy.mapper;

import com.atstudy.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 根据用户名查询到一个带有角色列表和菜单列表的用户对象
     * @param name
     * @return
     */
    Admin getAdminByName(@Param("name") String name);
}
