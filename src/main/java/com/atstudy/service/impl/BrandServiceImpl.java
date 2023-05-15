package com.atstudy.service.impl;

import com.atstudy.entity.Brand;
import com.atstudy.entity.bo.BrandSearchBo;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.mapper.BrandMapper;
import com.atstudy.service.BrandService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Resource
    private BrandMapper brandMapper;

    /**
     * 查询出所有品牌
     * @return
     */
    @Override
    public List<Brand> list() {
        List<Brand> list = brandMapper.selectList(new QueryWrapper<Brand>());

        return list;
    }

    @Override
    public List<Brand> listBySearchBo(BrandSearchBo searchBo, PageBo pageBo) {


        // 先查询出满足条件的总记录数据
        int count = brandMapper.getCount(searchBo);
        // 总台计算出总页数
        pageBo.setResultCount((long) count);
        // 再查询出满足条件的品牌列表
        List<Brand> list = brandMapper.listBySearchBo(searchBo, pageBo);

        return list;
    }

    /**
     * 删除一个批量
     * @param brandId
     * @return
     */
    @Override
    public boolean deleteOne(String brandId) {

        int i = brandMapper.deleteById(brandId);
        return i >= 1;
    }

    /**
     * 根据品牌id批量删除
     * @param idList
     * @return
     */
    @Override
    public boolean deleteByIdList(String[] idList) {
        int i = brandMapper.deleteBatchIds(Arrays.asList(idList));
        return i >= 1;
    }
}
