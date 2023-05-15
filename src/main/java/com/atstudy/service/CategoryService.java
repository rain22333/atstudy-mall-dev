package com.atstudy.service;

import com.atstudy.entity.Category;
import com.atstudy.entity.bo.CategoryAddBo;
import com.atstudy.entity.bo.CategorySearchBo;
import com.atstudy.entity.bo.CategoryUpdateBo;
import com.atstudy.entity.bo.PageBo;

import java.util.List;

public interface CategoryService {


    /**
     * 根据商品spuid查询出和这个商品spu关联的分类id列表
     * @param spuId
     * @return
     */
    List<Integer> getIdListBySpuId(Long spuId);

    /**
     * 更新一个分类
     * @param updateBo
     * @return
     */
    boolean updateByBo(CategoryUpdateBo updateBo);


    /**
     * 根据分类id查询信息
     * @return
     */
    Category getByCateId(Integer cateId);


    /**
     * 根据分类名称查询分类信息
     * @param cateName
     * @return
     */
    Category getByCateName(String cateName);

    /**
     * 根据筛选条件查询出符合条件的数据
     * @param searchBo
     * @param pageBo
     * @return
     */
    List<Category> listBySearchBo(CategorySearchBo searchBo, PageBo pageBo);


    /**
     * 查询出所有的分类
     * @return
     */
    List<Category> list();

    /**
     * 添加一个分类
     * @return
     */
    boolean insertOne(CategoryAddBo addBo);
}
