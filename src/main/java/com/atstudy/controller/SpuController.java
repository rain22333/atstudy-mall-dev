package com.atstudy.controller;

import com.atstudy.entity.Brand;
import com.atstudy.entity.Category;
import com.atstudy.entity.Spu;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.entity.bo.SpuAddBo;
import com.atstudy.entity.bo.SpuSearchBo;
import com.atstudy.entity.bo.SpuUpdateBo;
import com.atstudy.service.BrandService;
import com.atstudy.service.CategoryService;
import com.atstudy.service.SpuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/spu")
@Slf4j
public class SpuController {


    @Resource
    private SpuService spuService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private BrandService brandService;


    @RequestMapping("/delete")
    public String delete(Long spuId,Long[] idList) {
        boolean result;

        if(spuId != null){
            result = spuService.deleteOneById(spuId);
        }else {
            result = spuService.deleteByIdList(idList);
        }

        if (true) {
            return "redirect:/index/success?message=spu delete success&controller=spu/admin";
        } else {
            return "redirect:/index/error?message=spu delete error&controller=spu/admin";
        }
    }


    @RequestMapping("/alter")
    public String alter(SpuUpdateBo updateBo){
        boolean result = spuService.updateOneByUpdateBo(updateBo);

        if(result){
            return "redirect:/index/success?message=spu update success&controller=spu/admin";
        }else {
            return "redirect:/index/error?message=spu update error&controller=spu/admin";
        }
    }


    @RequestMapping("/update")
    public String update(Long spuId,Model model){

        // 根据spuid查询出当前spu的信息
        Spu spuById = spuService.getSpuById(spuId);
        // 将商品信息放到请求域中
        model.addAttribute("spu",spuById);

        // 查询到所有的分类和皮牌信息，放到请求域中
        model.addAttribute("brandList",brandService.list());
        model.addAttribute("cateList",categoryService.list());

        // 根据商品spuid查询出和这个商品关联的分类id列表
        List<Integer> idList = categoryService.getIdListBySpuId(spuId);
        model.addAttribute("idList",idList);

        return "spu/update";
    }


    @RequestMapping("/save")
    public String save(SpuAddBo addBo){

        boolean result = spuService.insertOne(addBo);

        if(result){
            return "redirect:/index/success?message=spu add success&controller=spu/admin";
        }else {
            return "redirect:/index/error?message=spu add error&controller=spu/admin";
        }
    }

    @RequestMapping("/add")
    public String add(Model model){

        model.addAttribute("brandList",brandService.list());
        model.addAttribute("cateList",categoryService.list());

        return "spu/add";
    }


    @RequestMapping("/admin")
    public String admin(SpuSearchBo searchBo, PageBo pageBo, Model model){


        // 将筛选条件和分页条件放到请求域中
        model.addAttribute("searchBo",searchBo);
        model.addAttribute("pageBo",pageBo);

        // 查询出满足条件的商品列表
        List<Spu> spuList = spuService.listBySearchBo(searchBo, pageBo);
        // 将满足条件的商品列表放到请求域中
        model.addAttribute("spuList",spuList);

        // 还需要将所有的分类列表也放到请求域中
        List<Category> list = categoryService.list();
        model.addAttribute("cateList",list);

        // 还需要将所有的品牌也放到请求域中
        List<Brand> brandList = brandService.list();
        model.addAttribute("brandList",brandList);

        return "spu/admin";
    }
}
