package com.atstudy.mapper;

import com.atstudy.entity.Category;
import com.atstudy.entity.bo.CategorySearchBo;
import com.atstudy.entity.bo.PageBo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {


    /**
     * 根据商品spuid查询到和这个商品关联的分类id列表
     * @param spuId
     * @return
     */
    List<Integer> getIdListBySpuId(Long spuId);


    /**
     * 更新一个分类信息
     *
     * @param category@return
     */
    int updateOne(@Param("category") Category category);


    /**
     * 根据分类编号查询分类信息
     * @param cateId
     * @return
     */
    Category getByCateId(@Param("cateId") Integer cateId);

    /**
     * 根据分类名称查询分类信息
     * @param cateName
     * @return
     */
    Category getByCateName(@Param("cateName") String cateName);


    /**
     * 添加一个分类
     * @param category
     * @return
     */
    int insertOne(@Param("category") Category category);


    /**
     * 根据父级分类编号查询分类
     * @param id
     * @return
     */
    Category getByParentId(@Param("id") Integer id);


    /**
     * 查询所有分类
     * @return
     */
    List<Category> list();

    /**
     * 根据筛选条件查询满足条件的分类集合
     * @param searchBo
     * @return
     */
    List<Category> listBySearchBo(@Param("searchBo") CategorySearchBo searchBo, @Param("pageBo")PageBo pageBo);


    /**
     * 查询满足条件的总记录数
     * @param searchBo
     * @return
     */
    int getCount(@Param("searchBo") CategorySearchBo searchBo);
}
