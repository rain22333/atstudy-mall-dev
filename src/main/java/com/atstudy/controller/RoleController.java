package com.atstudy.controller;

import com.atstudy.entity.Permission;
import com.atstudy.entity.Role;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.service.PermissionService;
import com.atstudy.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/role")
@Slf4j
public class RoleController {


    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;



    @RequestMapping("/delete")
    public String delete(Integer roleId,Integer[] idList){
        for (Integer integer : idList) {
            System.out.println(integer);
        }

        boolean result;
        // 判断到底是删除一个还是多个
        if(roleId != null){
            result = roleService.deleteById(roleId);
        }else {
            result = roleService.batchDeleteByIdS(idList);
        }

        if(result){
            return "redirect:/index/success?message=role delete success&controller=role/admin";
        }else {
            return "redirect:/index/error?message=role delete error&controller=role/admin";
        }
    }


    @RequestMapping("/alter")
    public String alter(Integer roleId,String roleName,Integer[] idList){

        log.info(Arrays.toString(idList));

        boolean result = roleService.updateOne(roleId, roleName, idList);

        if(result){
            return "redirect:/index/success?message=role add success&controller=role/admin";
        }else {
            return "redirect:/index/error?message=role add error&controller=role/admin";
        }
    }

    @RequestMapping("/update")
    public String update(Integer roleId,Model model){

        // 根据角色id查询到角色信息和权限列表放到请求域中
        model.addAttribute("role",roleService.getById(roleId));
        model.addAttribute("permissionList",permissionService.listVoByRoleId(roleId));

        return "role/update";
    }



    @RequestMapping("/validName")
    @ResponseBody
    public String validName(String roleName){

        Boolean nameExists = roleService.isNameExists(roleName);
        return nameExists.toString();
    }

    @RequestMapping("/save")
    public String save(String roleName,Integer[] idList){

        boolean insert = roleService.insert(roleName, idList);

        if(insert){
            return "redirect:/index/success?message=role add success&controller=role/admin";
        }else {
            return "redirect:/index/error?message=role add error&controller=role/admin";
        }
    }


    @RequestMapping("/add")
    public String add(Model model){

        // 获取到所有权限，将权限列表放到请求域中
        List<Permission> list = permissionService.list();
        model.addAttribute("permissionList",list);

        return "role/add";
    }


    @RequestMapping("/admin")
    public String admin(Role role, PageBo pageBo, Model model){


        List<Role> list = roleService.listByRoleAndPageBo(role, pageBo);

        // 将pageBo和list放到请求域中
        model.addAttribute("searchRole",role);
        model.addAttribute("pageBo",pageBo);
        model.addAttribute("roleList",list);

        for (Role role1 : list) {
            log.info(role1.toString());
        }

        return "role/admin";
    }

}
