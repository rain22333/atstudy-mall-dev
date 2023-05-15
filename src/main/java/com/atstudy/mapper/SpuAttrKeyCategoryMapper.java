package com.atstudy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface SpuAttrKeyCategoryMapper {

    /**
     * 批量删除属性键-分类关系
     * @param idList
     * @return
     */
    int deleteBatchByKeyIdList(@Param("idList") String[] idList);


    /**
     * 往属性键-分类关系表中添加数据
     * @param keyId
     * @param cateId
     * @return
     */
    int insert(@Param("keyId") String keyId, @Param("cateId") Integer cateId);


    /**
     * 根据属性键id查询出和这个属性键相关联的分类id列表
     * @param keyId
     * @return
     */
    List<Integer> listCateIdByKeyId(@Param("keyId") String keyId);


    /**
     * 根据属性键删除属性键-分类关系
     * @param keyId
     * @return
     */
    int deleteBatchByKeyId(@Param("keyId") String keyId);

}
