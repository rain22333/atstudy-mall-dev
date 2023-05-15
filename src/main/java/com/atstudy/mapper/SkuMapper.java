package com.atstudy.mapper;

import com.atstudy.entity.Sku;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.entity.bo.SkuSearchBo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SkuMapper extends BaseMapper<Sku> {


    /**
     * 添加一个sku
     * @param sku
     * @return
     */
    int insertOne(@Param("sku") Sku sku);


    /**
     * 根据筛选条件查询sku列表
     * @param searchBo
     * @param pageBo
     * @return
     */
    List<Sku> listBySearchBo(@Param("searchBo") SkuSearchBo searchBo,@Param("pageBo") PageBo pageBo);


    /**
     * 查询满足条件的记录数
     * @param searchBo
     * @return
     */
    Long getCount(@Param("searchBo") SkuSearchBo searchBo);

    /**
     * 根据spuid数组删除sku
     * @param idList
     * @return
     */
    int deleteBatchBySpuIdList(Long[] idList);
}
