package com.atstudy.service.impl;

import com.atstudy.entity.Admin;
import com.atstudy.entity.bo.AdminAddBo;
import com.atstudy.entity.bo.AdminSearchBo;
import com.atstudy.entity.bo.AdminUpdateBo;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.mapper.AdminMapper;
import com.atstudy.mapper.AdminRoleMapper;
import com.atstudy.service.AdminService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 登录验证 业务逻辑实现类
 */
@Service
public class AdminServiceImpl implements AdminService {


    @Resource
    private AdminMapper adminMapper;

    @Resource
    private AdminRoleMapper adminRoleMapper;



    // 根据用户名获取到 用户实体模型对象(带有角色列表)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin admin = adminMapper.getAdminByName(username);

        if(admin == null){
            // 如果用户不存在，则抛出异常
            throw new UsernameNotFoundException("账户名获取密码错误，请重新登录");
        }

        return admin;
    }

    /**
     * 根据筛选条件查询员工列表
     * @param adminSearchBo
     * @param pageBo
     * @return
     */
    @Override
    public List<Admin> listBySearchBo(AdminSearchBo adminSearchBo, PageBo pageBo) {

        // 查询到符合筛选条件的总记录数
        Long count = adminMapper.getCount(adminSearchBo);
        // 给分页模型对象·赋值
        // 给分页模型的总记录数赋值，给总记录数赋值的时候也会给总页数赋值
        pageBo.setResultCount(count);

        // 查询满足条件的员工列表
        List<Admin> admins = adminMapper.listByAdminSearchBo(adminSearchBo,pageBo);
        return admins;
    }

    @Transactional
    @Override
    public boolean saveOne(AdminAddBo adminAddBo) {


        // 给adminBo生成密码
        adminAddBo.setAdminPass(new BCryptPasswordEncoder().encode("abc123"));

        // 调用持久层添加管理员
        int insert = adminMapper.insert(adminAddBo);

        // 添加管理员-角色关系
        int i = adminMapper.batchInsertAdminRole(adminAddBo);

        return insert >= 1 && i >= 1;
    }

    @Override
    public Admin getAdminById(Integer id) {
        return adminMapper.getAdminById(id);
    }

    /**
     * 更新员工
     * @param adminUpdateBo
     * @return
     */
    @Transactional
    @Override
    public boolean updateByAdmin(AdminUpdateBo adminUpdateBo) {

        // 先更新admin数据表
        int i = adminMapper.updateByAdmin(adminUpdateBo);
        // 删除这个 员工原来的角色关联
        int i1 = adminMapper.batchDeleteRoleByAdminId(adminUpdateBo);
        // 重新添加员工-角色关系
        AdminAddBo adminAddBo = new AdminAddBo();

        // 给addBo中的roleList赋值
        adminAddBo.setRoleList(adminUpdateBo.getRoleList());
        // 还需要给addBo中的·员工编号赋值
        adminAddBo.setAdminId(adminUpdateBo.getAdminId());
        int i2 = adminMapper.batchInsertAdminRole(adminAddBo);

        return i >= 1 && i1 >= 1 && i2 >= 1;
    }

    /**
     * 根据员工id删除员工-角色关系
     * @param adminId
     * @return
     */
    @Transactional
    @Override
    public boolean deleteByAdminId(Integer adminId) {

        // 删除admin表中的数据
        int i = adminMapper.deleteById(adminId);
        // 还要删除admin-role关系

        Map<String,Object> map = new HashMap<>();
        map.put("admin_id",adminId);
        int i1 = adminRoleMapper.deleteByMap(map);

        return i >= 1 && i1 >= 1;
    }

    @Transactional
    @Override
    public boolean deleteByIdList(Integer[] idList) {

        // 先删除admin表中的数据，再删除admin-role关系表中的数据
        int i = adminMapper.deleteBatchIds(Arrays.asList(idList));

        // 再删除admin_role关系表的数据
        int i1 = adminRoleMapper.deleteByAdminIds(idList);

        // 如果admin和admin_role全部删除成功再返回true
        return i >= 1 && i1 >= 1;
    }
}
