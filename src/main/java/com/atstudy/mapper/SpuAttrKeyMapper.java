package com.atstudy.mapper;

import com.atstudy.entity.SpuAttrKey;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.entity.bo.SpuAttrKeySearchBo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SpuAttrKeyMapper extends BaseMapper<SpuAttrKey> {


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
    int deleteOneByKeyId(@Param("keyId") String keyId);

    /**
     * 根据属性键id批量删除
     * @param idList
     * @return
     */
    int deleteBatchByIdList(@Param("idList") String[] idList);

    /**
     * 更新一个数据键
     * @param spuAttrKey
     * @return
     */
    int updateOne(@Param("spuAttrKey") SpuAttrKey spuAttrKey);



    /**
     * 根据属性键id查询出带有属性值列表的属性键
     * @param keyId
     * @return
     */
    SpuAttrKey getByKeyId(@Param("keyId") String keyId);


    /**
     * 往属性键表添加一条记录
     * @param spuAttrKey
     * @return
     */
    int insertOne(@Param("spuAttrKey") SpuAttrKey spuAttrKey);


    /**
     * 根据筛选条件查询出 带有属性值列表的属性键 列表
     * @return
     * @param searchBo
     * @param pageBo
     */
    List<SpuAttrKey> listBySearchBo(@Param("searchBo") SpuAttrKeySearchBo searchBo,@Param("pageBo") PageBo pageBo);


    /**
     * 查询出满足条件的总记录数
     * @param searchBo
     * @return
     */
    Long getCount(@Param("searchBo") SpuAttrKeySearchBo searchBo);
}
