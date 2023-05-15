package com.atstudy.controller;

import com.atstudy.entity.bo.BrandSearchBo;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.service.BrandService;
import com.atstudy.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Arrays;

@Controller
@RequestMapping("/brand")
@Slf4j
public class BrandController {


    @Resource
    private BrandService brandService;

    @Resource
    private CategoryService categoryService;




    @RequestMapping("/delete")
    public String delete(String brandId,String[] idList ){

        boolean result;

        if (brandId != null){
            result = brandService.deleteOne(brandId);
        }else {
            result = brandService.deleteByIdList(idList);
        }

        if(result){
            return "redirect:/index/success?message=brand delete success&controller=brand/admin";
        }else {
            return "redirect:/index/error?message=brand delete error&controller=brand/admin";
        }
    }

    @RequestMapping("/add")
    public String add(){
        return "brand/add";
    }


    @RequestMapping("/admin")
    public String admin(BrandSearchBo searchBo, PageBo pageBo, Model model){


        log.info(searchBo.toString());
        // 将筛选条件和分页条件放到请求域中，方便做筛选条件持久化
        model.addAttribute("searchBo",searchBo);
        model.addAttribute("pageBo",pageBo);
        // 将所有分类放到请求域中
        model.addAttribute("cateList",categoryService.list());

        // 将满足条件的品牌列表放到请求域中
        model.addAttribute("brandList",brandService.listBySearchBo(searchBo, pageBo));
        return "/brand/admin";
    }
}
