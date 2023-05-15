package com.atstudy.controller;

import com.atstudy.entity.Sku;
import com.atstudy.entity.SpuAttrKey;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.entity.bo.SkuAddBo;
import com.atstudy.entity.bo.SkuSearchBo;
import com.atstudy.entity.bo.SkuUpdateBo;
import com.atstudy.service.SkuService;
import com.atstudy.service.SpuAttrKeyService;
import com.atstudy.service.SpuService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/sku")
@Slf4j
public class SkuController {

    @Resource
    private SkuService skuService;

    @Resource
    private SpuService spuService;

    @Resource
    private SpuAttrKeyService spuAttrKeyService;


    @RequestMapping("/delete")
    public String delete(Long skuId,Long[] idList){

        boolean result;

        if(skuId != null){
            result = skuService.deleteById(skuId);
        }else {
            result = skuService.deleteByIdList(idList);
        }

        if(result){
            return "redirect:/index/success?message=sku delete success&controller=sku/admin";
        }else {
            return "redirect:/index/error?message=sku delete error&controller=sku/admin";
        }
    }


    @RequestMapping("/alter")
    public String alter(SkuUpdateBo updateBo){

        boolean result = skuService.updateOne(updateBo);
        log.info(updateBo.toString());

        if(result){
            return "redirect:/index/success?message=sku update success&controller=sku/admin";
        }else {
            return "redirect:/index/error?message=sku update error&controller=sku/admin";
        }
    }


    @RequestMapping("/update")
    public String update(Long skuId,Model model){

        Sku skuById = skuService.getSkuById(skuId);
        model.addAttribute("sku",skuById);
        model.addAttribute("spuList",spuService.list());


        return "sku/update";
    }


    @RequestMapping("/save")
    public String save(SkuAddBo skuAddBo){
        log.info(skuAddBo.toString());

        boolean result = skuService.insertOne(skuAddBo);
        if(result){
            return "redirect:/index/success?message=sku add success&controller=sku/admin";
        }else {
            return "redirect:/index/error?message=sku add error&controller=sku/admin";
        }

    }



    @RequestMapping("/getKeyValue")
    @ResponseBody
    public Object getSkuKeyValueList(Long spuId){

        // 根据商品spuid查询到这个商品spu所有的带属性值列表的规格属性键
        List<SpuAttrKey> keyList = spuAttrKeyService.getKeyListBySpuId(spuId);

        return keyList;
    }




    @RequestMapping("/add")
    public String add(Model model){
        // 需要查询出所有商品spu，放到请求域中
        model.addAttribute("spuList",spuService.list());
        return "sku/add";
    }

    @RequestMapping("/admin")
    public String admin(SkuSearchBo searchBo, PageBo pageBo, Model model){

        model.addAttribute("searchBo",searchBo);
        model.addAttribute("pageBo",pageBo);

        // 查询出满足条件的sku列表，放到请求域中
        List<Sku> skus = skuService.listBySearchBo(searchBo, pageBo);
        model.addAttribute("skuList",skus);
        return "sku/admin";
    }
}
