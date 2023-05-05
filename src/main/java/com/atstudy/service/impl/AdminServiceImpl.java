package com.atstudy.service.impl;

import com.atstudy.entity.Admin;
import com.atstudy.entity.Role;
import com.atstudy.mapper.AdminMapper;
import com.atstudy.mapper.RoleMapper;
import com.atstudy.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 登录验证 业务逻辑实现类
 */
@Service
public class AdminServiceImpl implements AdminService {


    @Resource
    private AdminMapper adminMapper;



    // 根据用户名获取到 用户实体模型对象(带有角色列表)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin admin = adminMapper.getAdminByName(username);

        if(admin == null){
            // 如果用户不存在，则抛出异常
            throw new UsernameNotFoundException("账户名获取密码错误，请重新登录");
        }

        return admin;
    }
}
