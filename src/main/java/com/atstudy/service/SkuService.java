package com.atstudy.service;

import com.atstudy.entity.Sku;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.entity.bo.SkuAddBo;
import com.atstudy.entity.bo.SkuSearchBo;
import com.atstudy.entity.bo.SkuUpdateBo;

import java.util.List;

public interface SkuService {

    /**
     * 删除一个sku
     * @param skuId
     * @return
     */
    boolean deleteById(Long skuId);


    /**
     * 根据id批量删除sku
     * @param idList
     * @return
     */
    boolean deleteByIdList(Long[] idList);


    /**
     * 更新一个sku
     *
     * @param updateBo@return
     */
    boolean updateOne(SkuUpdateBo updateBo);


    /**
     * 根据skuid查询数据
     * @param skuId
     * @return
     */
    Sku getSkuById(Long skuId);


    /**
     * 添加一个sku
     * @param addBo
     * @return
     */
    boolean insertOne(SkuAddBo addBo);

    /**
     * 根据筛选条件查询出sku列表
     * @param searchBo
     * @param pageBo
     * @return
     */
    List<Sku> listBySearchBo(SkuSearchBo searchBo, PageBo pageBo);
}
