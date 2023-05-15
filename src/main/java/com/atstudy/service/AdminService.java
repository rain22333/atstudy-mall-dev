package com.atstudy.service;

import com.atstudy.entity.Admin;
import com.atstudy.entity.bo.AdminAddBo;
import com.atstudy.entity.bo.AdminSearchBo;
import com.atstudy.entity.bo.AdminUpdateBo;
import com.atstudy.entity.bo.PageBo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 用户类的业务逻辑层接口
 * 登录验证 业务逻辑层 接口
 */
public interface AdminService extends UserDetailsService {

    /**
     * 根据筛选条件查询符合条件的员工列表
     * @param adminSearchBo
     * @param pageBo
     * @return
     */
    List<Admin> listBySearchBo(AdminSearchBo adminSearchBo, PageBo pageBo);

    boolean saveOne(AdminAddBo adminAddBo);


    /**
     * 根据id获取到员工对象
     * @param id
     * @return
     */
    Admin getAdminById(Integer id);

    boolean updateByAdmin(AdminUpdateBo adminUpdateBo);

    /**
     * 根据员工编号删除员工
     * @param adminId
     * @return
     */
    boolean deleteByAdminId(Integer adminId);


    /**
     * 根据id数组批量删除员工
     * @param idList
     * @return
     */
    boolean deleteByIdList(Integer[] idList);
}
