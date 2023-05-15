package com.atstudy.service.impl;

import com.atstudy.entity.Sku;
import com.atstudy.entity.Spu;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.entity.bo.SpuAddBo;
import com.atstudy.entity.bo.SpuSearchBo;
import com.atstudy.entity.bo.SpuUpdateBo;
import com.atstudy.mapper.SkuMapper;
import com.atstudy.mapper.SpuMapper;
import com.atstudy.service.SpuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class SpuServiceImpl implements SpuService {


    @Resource
    private SpuMapper spuMapper;

    @Resource
    private SkuMapper skuMapper;

    @Override
    public List<Spu> list() {
        return spuMapper.list();
    }

    @Transactional
    @Override
    public boolean deleteOneById(Long spuId) {
        // 先删除spu表，再删除当前spu下的所有的sku
        int i1 = spuMapper.deleteById(spuId);

        // 根据spuid删除sku表的数据
        QueryWrapper<Sku> wrapper = new QueryWrapper<>();
        wrapper.eq("sku_spu_id",spuId);
        int i2 = skuMapper.delete(wrapper);

        return i1 >= 1 && i2 >= 1;
    }

    @Transactional
    @Override
    public boolean deleteByIdList(Long[] idList) {
        // 先删除spu表的数据
        int i1 = spuMapper.deleteBatchIds(Arrays.asList(idList));

        // 再删除当前spu下的sku
        int i2 = skuMapper.deleteBatchBySpuIdList(idList);
        return i1 >= 1 && i2 >= 1;
    }

    @Transactional
    @Override
    public boolean updateOneByUpdateBo(SpuUpdateBo updateBo) {
        // 先更新spu表
        Spu spu = new Spu();
        spu.setSpuId(updateBo.getSpuId());
        spu.setSpuName(updateBo.getSpuName());
        spu.setSpuTitle(updateBo.getSpuTitle());
        spu.setSpuIntroduction(updateBo.getSpuIntroduction());
        spu.setSpuSkuType(updateBo.getSpuSkuType());
        spu.setSpuStatus(updateBo.getSpuStatus());
        spu.setSpuBrandId(updateBo.getSpuBrandId());
        // 设置创建更新时间
        LocalDateTime now = LocalDateTime.now();
        spu.setUpdatetime(now);

        int i1 = spuMapper.updateOne(spu);

        // 删除原来的分类-商品关系
        int i2 = spuMapper.deleteSpuCateBySpuId(updateBo.getSpuId());

        // 重新添加分类-商品spu关系
        int i3 = spuMapper.insertCateSpuByIdList(updateBo.getSpuId(), updateBo.getCateId());


        return i1 >= 1 && i2 >= 1 && i3 >= 1;
    }

    @Override
    public Spu getSpuById(Long spuId) {
        Spu spu = spuMapper.getSpuById(spuId);
        return spu;
    }

    @Override
    public List<Spu> listBySearchBo(SpuSearchBo searchBo, PageBo pageBo) {

        // 先查询出满足条件的总记录数，给pageBo的总记录数赋值(可以计算出总页数)
        int count = spuMapper.getCount(searchBo);
        pageBo.setResultCount((long) count);

        // 查询出满足条件的商品spu列表
        List<Spu> spuList = spuMapper.listBySearchBo(searchBo, pageBo);
        return spuList;
    }

    /**
     * 添加一个商品spu
     * @param spuAddBo
     * @return
     */
    @Transactional
    @Override
    public boolean insertOne(SpuAddBo spuAddBo) {

        // 先添加商品spu表
        // 创建一个Spu对象，赋值
        Spu spu = new Spu();
        spu.setSpuName(spuAddBo.getSpuName());
        spu.setSpuTitle(spuAddBo.getSpuTitle());
        spu.setSpuIntroduction(spuAddBo.getSpuIntroduction());
        spu.setSpuSkuType(spuAddBo.getSpuSkuType());
        spu.setSpuStatus(spuAddBo.getSpuStatus());
        spu.setSpuBrandId(spuAddBo.getSpuBrandId());
        // 设置创建更新时间
        LocalDateTime now = LocalDateTime.now();
        spu.setCreatetime(now);
        spu.setUpdatetime(now);


        int i1 = spuMapper.insertOne(spu);

        // 再添加分类-商品spu关系表
        int i2 = spuMapper.insertCateSpu(spu.getSpuId(), spuAddBo.getCateId());
        return i1 >= 1 && i2 >= 1;
    }
}
