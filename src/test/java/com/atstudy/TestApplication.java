package com.atstudy;

import com.atstudy.entity.Menu;
import com.atstudy.entity.Role;
import com.atstudy.mapper.MenuMapper;
import com.atstudy.mapper.RoleMapper;
import com.atstudy.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
public class TestApplication {


    @Resource
    private AdminService adminService;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private MenuMapper menuMapper;


    @Test
    public void testMenuMapper(){
        List<Menu> menus = menuMapper.listByAdminId(2);
        for (Menu menu : menus) {
            log.info(menu.toString());
        }
    }


    @Test
    public void testRoleMapper(){
        List<Role> list = roleMapper.listByuUrl("/userorder/admin");
        for (Role role : list) {
            System.out.println(role);
        }
    }

    @Test
    public void  testAdminService(){
        UserDetails admin = adminService.loadUserByUsername("admin");
        System.out.println(admin);
    }
}
