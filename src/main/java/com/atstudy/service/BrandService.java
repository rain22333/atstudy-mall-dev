package com.atstudy.service;

import com.atstudy.entity.Brand;
import com.atstudy.entity.bo.BrandSearchBo;
import com.atstudy.entity.bo.PageBo;

import java.util.List;

public interface BrandService {


    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> list();


    /**
     * 根据筛选条件查询出品牌列表
     * @param searchBo
     * @param pageBo
     * @return
     */
    List<Brand> listBySearchBo(BrandSearchBo searchBo, PageBo pageBo);

    /**
     * 删除单个品牌
     * @param brandId
     * @return
     */
    boolean deleteOne(String brandId);

    /**
     * 根据id批量删除品牌
     * @param idList
     * @return
     */
    boolean deleteByIdList(String[] idList);
}
