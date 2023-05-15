package com.atstudy.mapper;

import com.atstudy.entity.AdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminRoleMapper extends BaseMapper<AdminRole> {


    /**
     * 根据id列表批量删除员工-角色关系
     * @param idList
     * @return
     */
    int deleteByAdminIds(@Param("idList") Integer[] idList);
}
