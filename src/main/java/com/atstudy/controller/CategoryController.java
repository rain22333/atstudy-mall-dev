package com.atstudy.controller;

import com.atstudy.entity.Category;
import com.atstudy.entity.bo.CategoryAddBo;
import com.atstudy.entity.bo.CategorySearchBo;
import com.atstudy.entity.bo.CategoryUpdateBo;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;


    @RequestMapping("/alter")
    public String alter(CategoryUpdateBo updateBo){

        boolean result = categoryService.updateByBo(updateBo);

        if(result){
            return "redirect:/index/success?message=category update success&controller=category/admin";
        }else {
            return "redirect:/index/error?message=category update error&controller=category/admin";
        }
    }


    @RequestMapping("/update")
    public String update(Integer cateId,Model model){
        // 根据id查询到分类信息放到请求域中
        Category byCateId = categoryService.getByCateId(cateId);
        model.addAttribute("adminCategory",byCateId);

        // 查询到所有分类放到请求域中
        List<Category> list = categoryService.list();
        model.addAttribute("cateList",list);

        return "category/update";
    }


    @RequestMapping("/save")
    public String save(CategoryAddBo addBo){
        boolean result = categoryService.insertOne(addBo);

        if(result){
            return "redirect:/index/success?message=category add success&controller=category/admin";
        }else {
            return "redirect:/index/error?message=category add error&controller=category/admin";
        }
    }

    @RequestMapping("/add")
    public String add(Model model){

        List<Category> list = categoryService.list();
        model.addAttribute("cateList",list);

        return "category/add";
    }


    @RequestMapping("/admin")
    public String admin(CategorySearchBo searchBo, PageBo pageBo, Model model){

        // 先将搜索模型对象和分页模型对象放到请求域中
        model.addAttribute("searchBo",searchBo);
        model.addAttribute("pageBo",pageBo);
        // 将满足条件的分类集合放到请求域中
        List<Category> categories = categoryService.listBySearchBo(searchBo, pageBo);
        model.addAttribute("categoryList",categories);
        model.addAttribute("categoryAll",categoryService.list());


        return "category/admin";
    }


    @ResponseBody
    @RequestMapping("/validName")
    public String validName(String cateName){
        Category byCateName = categoryService.getByCateName(cateName);

        return byCateName == null ? "true" : "false";
    }
}
