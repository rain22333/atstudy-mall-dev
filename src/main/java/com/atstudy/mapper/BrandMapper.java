package com.atstudy.mapper;

import com.atstudy.entity.Brand;
import com.atstudy.entity.bo.BrandSearchBo;
import com.atstudy.entity.bo.PageBo;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface BrandMapper extends BaseMapper<Brand> {

    /**
     * 根据筛选条件查询品牌列表
     * @param searchBo
     * @param pageBo
     * @return
     */
    List<Brand> listBySearchBo(@Param("searchBo") BrandSearchBo searchBo,@Param("pageBo") PageBo pageBo);

    /**
     * 根据筛选条件查询出满足条件的总记录数
     * @param searchBo
     * @return
     */
    int getCount(@Param("searchBo") BrandSearchBo searchBo);




    Brand getBrandById(@Param("brandId") String brandId);

}
