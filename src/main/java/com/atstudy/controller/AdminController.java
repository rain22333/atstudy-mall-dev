package com.atstudy.controller;

import com.atstudy.entity.Admin;
import com.atstudy.entity.Role;
import com.atstudy.entity.bo.AdminAddBo;
import com.atstudy.entity.bo.AdminSearchBo;
import com.atstudy.entity.bo.AdminUpdateBo;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.service.AdminService;
import com.atstudy.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Resource
    private AdminService adminService;

    @Resource
    private RoleService roleService;

    @RequestMapping("/delete")
    public String delete(Integer adminId,Integer[] idList){

        boolean result;
        if(adminId != null){
           result = adminService.deleteByAdminId(adminId);
        }else {
            result = adminService.deleteByIdList(idList);
        }

        if(result){
            return "redirect:/index/success?message=admin delete success&controller=admin/admin";
        }else {
            return "redirect:/index/error?message=admin delete error&controller=admin/admin";
        }
    }



    @RequestMapping("/alter")
    public String alter(AdminUpdateBo adminUpdateBo){


        boolean result = adminService.updateByAdmin(adminUpdateBo);

        if(result){
            return "redirect:/index/success?message=admin update success&controller=admin/admin";
        }else {
            return "redirect:/index/error?message=admin update error&controller=admin/admin";
        }
    }


    @RequestMapping("/update")
    public String update(Integer adminId,Model model){
        Admin adminById = adminService.getAdminById(adminId);

        // 将根据id获取到的admin对象放到请求域中
        model.addAttribute("admin",adminById);
        // 将所有角色查询出来放到请求域中
        model.addAttribute("roleList",roleService.list());
        return "admin/update";
    }




    @RequestMapping("/save")
    public String save( AdminAddBo adminAddBo){

        boolean b = adminService.saveOne(adminAddBo);
        if(b){
            return "redirect:/index/success?message=admin add success&controller=admin/admin";
        }else {
            return "redirect:/index/error?message=admin add error&controller=admin/admin";
        }



    }

    @RequestMapping("/add")
    public String add(Model model){

        // 查询所有角色并放到请求域中
        List<Role> list = roleService.list();

        model.addAttribute("roleList",list);

        return "admin/add";
    }


    @RequestMapping("/admin")
    public String admin(AdminSearchBo adminSearchBo, PageBo pageBo, Model model){


        // 获取到了符合条件的员工列表，并且给分页模型赋值了
        List<Admin> admins = adminService.listBySearchBo(adminSearchBo, pageBo);

        // 将员工列表和分页模型全部放入请求域中
        model.addAttribute("adminList",admins);
        model.addAttribute("pageBo",pageBo);

        // 将查询条件放到请求域中，做·持久化
        model.addAttribute("searchBo",adminSearchBo);
        // 将所有的角色放到请求域中
        model.addAttribute("roleList",roleService.list());


        return "admin/admin";
    }
}
