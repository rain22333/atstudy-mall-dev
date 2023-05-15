package com.atstudy.mapper;

import com.atstudy.entity.SpuAttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SpuAttrValueMapper extends BaseMapper<SpuAttrValue> {


    /**
     * 根据传入的属性键的id数组批量删除
     * @param idList
     * @return
     */
    int deleteBatchByKeyIdList(@Param("idList") String[] idList);


    /**
     * 批量添加属性值
     * @param list
     * @return
     */
    int insertBatchByList(@Param("list") List<SpuAttrValue> list,@Param("keyId") String keyId);


    /**
     * 根据属性键id查询出属性值列表
     * @param keyId
     * @return
     */
    List<SpuAttrValue> listByKeyId(@Param("keyId") String keyId);
}
