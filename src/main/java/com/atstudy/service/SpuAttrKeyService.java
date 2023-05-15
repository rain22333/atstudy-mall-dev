package com.atstudy.service;

import com.atstudy.entity.SpuAttrKey;
import com.atstudy.entity.bo.AtteKeyAddBo;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.entity.bo.SpuAttrKeySearchBo;
import com.atstudy.entity.bo.SpuAttrUpdateBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpuAttrKeyService {

    /**
     * 根据商品spuid查询出带有属性值列表的规格属性键列表
     * @param spuId
     * @return
     */
    List<SpuAttrKey> getKeyListBySpuId(Long spuId);


    /**
     * 删除一个属性键
     * @param keyId
     * @return
     */
    boolean deleteOne(String keyId);


    /**
     * 根据属性键id批量删除
     * @param idList
     * @return
     */
    boolean deleteBatchByIdList(String[] idList);


    /**
     * 根据SpuAttrUpdateBo更新属性键
     * @param updateBo
     * @return
     */
    boolean updateByBo(SpuAttrUpdateBo updateBo);


    /**
     * 根据属性键id查询到和这个属性键相关联的分类iud列表
     * @param keyId
     * @return
     */
    List<Integer> listCateIdByKeyId(String keyId);


    /**
     * 根据属性键id 查询出这个属性键的信息(包含属性值列表)
     * @param keyId
     * @return
     */
    SpuAttrKey getByKeyId(String keyId);


    /**
     * 添加属性键
     * @param addBo
     * @return
     */
    boolean insertOne(AtteKeyAddBo addBo);


    /**
     * 根据筛选条件查询出 带有属性值列表的属性键 列表
     * @param searchBo
     * @param pageBo
     * @return
     */
    List<SpuAttrKey> listBySearchBo(SpuAttrKeySearchBo searchBo, PageBo pageBo);



}
