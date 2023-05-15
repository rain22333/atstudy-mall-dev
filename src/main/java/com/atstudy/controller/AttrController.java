package com.atstudy.controller;

import com.atstudy.entity.SpuAttrKey;
import com.atstudy.entity.bo.AtteKeyAddBo;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.entity.bo.SpuAttrKeySearchBo;
import com.atstudy.entity.bo.SpuAttrUpdateBo;
import com.atstudy.service.CategoryService;
import com.atstudy.service.SpuAttrKeyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/attr")
public class AttrController {


    @Resource
    private SpuAttrKeyService spuAttrKeyService;

    @Resource
    private CategoryService categoryService;




    @RequestMapping("/delete")
    public String delete(String keyId,String[] idList){

        boolean result;
        if (keyId != null){
            result = spuAttrKeyService.deleteOne(keyId);
        }else {
            result = spuAttrKeyService.deleteBatchByIdList(idList);
        }

        if(result){
            return "redirect:/index/success?message=attr delete success&controller=attr/admin";
        }else {
            return "redirect:/index/error?message=attr delete error&controller=attr/admin";
        }
    }


    @RequestMapping("/alter")
    public String alter(SpuAttrUpdateBo updateBo){

        boolean result = spuAttrKeyService.updateByBo(updateBo);

        log.info(updateBo.toString());
        if(result){
            return "redirect:/index/success?message=attr add success&controller=attr/admin";
        }else {
            return "redirect:/index/error?message=attr add error&controller=attr/admin";
        }

    }

    @RequestMapping("/update")
    public String update(String keyId,Model model){
        // 根据传入进来的属性键id查询到带有属性值列表 的属性键对象
        SpuAttrKey spuAttrKey = spuAttrKeyService.getByKeyId(keyId);
        model.addAttribute("attrKey",spuAttrKey);

        // 根据属性键id查询出这个属性键相关联的分类id
        List<Integer> cateIdList = spuAttrKeyService.listCateIdByKeyId(keyId);
        model.addAttribute("cateIdList",cateIdList);

        // 查询出所有的分类
        model.addAttribute("cateList",categoryService.list());


        return "attr/update";
    }



    @RequestMapping("/save")
    public String save(AtteKeyAddBo addBo){
        boolean result = spuAttrKeyService.insertOne(addBo);

        if(result){
            return "redirect:/index/success?message=attr add success&controller=attr/admin";
        }else {
            return "redirect:/index/error?message=attr add error&controller=attr/admin";
        }
    }


    @RequestMapping("/add")
    public String add(Model model){
        // 将所有的分类放到请求域中
        model.addAttribute("cateList",categoryService.list());
        return "attr/add";
    }

    @RequestMapping("/admin")
    public String admin(SpuAttrKeySearchBo searchBo, PageBo pageBo, Model model){

        // 将筛选条件放到请求域中做筛选数据持久化
        model.addAttribute("searchBo",searchBo);
        model.addAttribute("pageBo",pageBo);
        // 将查询出的数据也放到请求域中
        List<SpuAttrKey> keyList = spuAttrKeyService.listBySearchBo(searchBo, pageBo);
        model.addAttribute("keyList",keyList);

        return "attr/admin";
    }
}
