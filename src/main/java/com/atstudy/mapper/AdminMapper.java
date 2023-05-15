package com.atstudy.mapper;

import com.atstudy.entity.Admin;
import com.atstudy.entity.bo.AdminAddBo;
import com.atstudy.entity.bo.AdminSearchBo;
import com.atstudy.entity.bo.AdminUpdateBo;
import com.atstudy.entity.bo.PageBo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 根据用户名查询到一个带有角色列表和菜单列表的用户对象
     * @param name
     * @return
     */
    Admin getAdminByName(@Param("name") String name);


    /**
     * 根据筛选条件查询出符合筛选条件的总记录数
     * @param adminSearchBo
     * @return
     */
    Long getCount(@Param("adminSearchBo") AdminSearchBo adminSearchBo);


    /**
     * 根据筛选条件查询员工列表
     * @param adminSearchBo
     * @return
     */
    List<Admin> listByAdminSearchBo(@Param("adminSearchBo") AdminSearchBo adminSearchBo,
                                    @Param("pageBo") PageBo pageBo);


    /**
     * 批量插入用户-角色关系
     * @return
     */
    int batchInsertAdminRole(@Param("adminAddBo") AdminAddBo adminAddBo);


    /**
     * 往admin表中插入一条数据
     * @param adminAddBo
     * @return
     */
    int insert(@Param("adminAddBo") AdminAddBo adminAddBo);


    /**
     * 根据管理员编号获取到对象
     * @param id
     * @return
     */
    Admin getAdminById(@Param("id") Integer id);

    /**
     * 根据传入的admin对象更新数据
     * @param admin
     * @return
     */
    int updateByAdmin(@Param("admin") AdminUpdateBo admin);


    /**
     * 根据员工编号批量删除员工角色关系
     * @return
     */
    int batchDeleteRoleByAdminId(@Param("admin") AdminUpdateBo admin);



}
