package com.atstudy.service;

import com.atstudy.entity.Spu;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.entity.bo.SpuAddBo;
import com.atstudy.entity.bo.SpuSearchBo;
import com.atstudy.entity.bo.SpuUpdateBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpuService {


    /**
     * 查询所有商品spu
     * @return
     */
    List<Spu> list();


    /**
     * 删除一个spu
     * @param spuId
     * @return
     */
    boolean deleteOneById(Long spuId);

    /**
     * 删除多个spu
     * @param idList
     * @return
     */
    boolean deleteByIdList(Long[] idList);

    /**
     * 根据updateBo更新spu
     * @param updateBo
     * @return
     */
    boolean updateOneByUpdateBo(SpuUpdateBo updateBo);

    /**
     * 根据商品spuid查询出数据
     * @return
     */
    Spu getSpuById(Long spuId);

    /**
     * 根据筛选条件查询商品spu列表
     * @param searchBo
     * @param pageBo
     * @return
     */
    List<Spu> listBySearchBo(SpuSearchBo searchBo, PageBo pageBo);

    /**
     * 添加一个商品spu
     * @param spuAddBo
     * @return
     */
    boolean insertOne(SpuAddBo spuAddBo);
}
