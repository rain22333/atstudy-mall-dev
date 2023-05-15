package com.atstudy.mapper;

import com.atstudy.entity.Spu;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.entity.bo.SpuSearchBo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SpuMapper extends BaseMapper<Spu> {





    /**查询所有商品spu*/
    List<Spu> list();


    /**
     * 根据spuid和cateIdList添加分类-商品spu关系
     * @param spuId  商品spuid
     * @param idList   分类id数组
     * @return
     */
    int insertCateSpuByIdList(@Param("spuId") Long spuId,@Param("idList") Integer[] idList);



    /**
     * 根据商品spuid删除分类-商品spu关系
     * @return
     */
    int deleteSpuCateBySpuId(@Param("spuId") Long spuId);



    /**
     * 更新一个spu
     * @param spu
     * @return
     */
    int updateOne(@Param("spu") Spu spu);



    /**
     * 根据商品spuid查询出商品信息
     * @param spuId
     * @return
     */
    Spu getSpuById(@Param("spuId") Long spuId);

    /**
     * 添加分类-商品spu关系
     * @param spuId
     * @param cateId
     * @return
     */
    int insertCateSpu(@Param("spuId") Long spuId,@Param("cateId") Integer cateId);



    /**
     * 添加一个商品spu
     * @return
     */
    int insertOne(@Param("spu") Spu spu);


    /**
     * 根据筛选条件查询出满足条件的商品Spu列表
     * @param searchBo
     * @param pageBo
     * @return
     */
    List<Spu> listBySearchBo(@Param("searchBo") SpuSearchBo searchBo,@Param("pageBo") PageBo pageBo);


    /**
     * 根据筛选条件查询出满足条件的总记录数
     * @param searchBo
     * @return
     */
    int getCount(@Param("searchBo") SpuSearchBo searchBo);
}

