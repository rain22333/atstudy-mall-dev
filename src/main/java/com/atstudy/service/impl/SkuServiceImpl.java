package com.atstudy.service.impl;

import com.alibaba.fastjson2.JSON;
import com.atstudy.entity.Sku;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.entity.bo.SkuAddBo;
import com.atstudy.entity.bo.SkuSearchBo;
import com.atstudy.entity.bo.SkuUpdateBo;
import com.atstudy.entity.vo.SkuSpuAttrVo;
import com.atstudy.mapper.SkuMapper;
import com.atstudy.service.SkuService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class SkuServiceImpl implements SkuService {


    @Resource
    private SkuMapper skuMapper;


    /**
     * 删除一个sku
     * @param skuId
     * @return
     */
    @Override
    public boolean deleteById(Long skuId) {
        int i = skuMapper.deleteById(skuId);

        return i >= 1;
    }

    /**
     * 删除多个sku
     * @param idList
     * @return
     */
    @Override
    public boolean deleteByIdList(Long[] idList) {

        int  i = skuMapper.deleteBatchIds(Arrays.asList(idList));

        return i >= 1;
    }

    /**
     * 更新商品sku
     * @param updateBo@return
     * @return
     */
    @Override
    public boolean updateOne(SkuUpdateBo updateBo) {

        // 创建一个Sku用于更新
        Sku sku = new Sku();
        sku.setSkuId(updateBo.getSkuId());
        sku.setSkuName(updateBo.getSkuName());
        sku.setSkuSpuId(updateBo.getSkuSpuId());
        sku.setSkuPrice(updateBo.getSkuPrice());
        sku.setSkuOriginalprice(updateBo.getSkuOriginalprice());
        sku.setSkuCostprice(updateBo.getSkuCostprice());
        sku.setSkuQuantity(updateBo.getSkuQuantity());
        // 设置创建更新时间
        LocalDateTime now = LocalDateTime.now();
        sku.setUpdatetime(now);

        // 设置描述
        List<SkuSpuAttrVo> skuSpuAttrList = new ArrayList<>();
        // 遍历添加数据中的数据键列表
        for (int i = 0; i < updateBo.getKeyList().size(); i++) {
            // 创建vo对象用于承载描述信息
            SkuSpuAttrVo vo = new SkuSpuAttrVo();
            // 给vo赋值
            vo.setSpu_id(updateBo.getSkuSpuId());
            vo.setKey_id(updateBo.getKeyList().get(i).getKeyId());
            vo.setKey_name(updateBo.getKeyList().get(i).getKeyName());
            vo.setValue_id(updateBo.getValueList().get(i).getId());
            vo.setValue_name(updateBo.getValueList().get(i).getValueName());
            skuSpuAttrList.add(vo);
        }

        String s = JSON.toJSONString(skuSpuAttrList);
        // 给spu的sku描述赋值
        sku.setSkuSpuattr(s);


        int i = skuMapper.updateById(sku);


        return i >= 1;
    }

    @Override
    public Sku getSkuById(Long skuId) {
        Sku sku = skuMapper.selectById(skuId);
        return sku;
    }

    /**
     * 添加一个商品sku
     * @param addBo
     * @return
     */
    @Override
    public boolean insertOne(SkuAddBo addBo) {

        // 创建出一个sku用于添加数据
        Sku sku = new Sku();
        sku.setSkuName(addBo.getSkuName());
        sku.setSkuSpuId(addBo.getSkuSpuId());
        sku.setSkuPrice(addBo.getSkuPrice());
        sku.setSkuOriginalprice(addBo.getSkuOriginalprice());
        sku.setSkuCostprice(addBo.getSkuCostprice());
        sku.setSkuQuantity(addBo.getSkuQuantity());
        // 设置创建更新时间
        LocalDateTime now = LocalDateTime.now();
        sku.setCreatetime(now);
        sku.setUpdatetime(now);


        // 设置描述
        List<SkuSpuAttrVo> skuSpuAttrList = new ArrayList<>();
        // 遍历添加数据中的数据键列表
        for (int i = 0; i < addBo.getKeyList().size(); i++) {
            // 创建vo对象用于承载描述信息
            SkuSpuAttrVo vo = new SkuSpuAttrVo();
            // 给vo赋值
            vo.setSpu_id(addBo.getSkuSpuId());
            vo.setKey_id(addBo.getKeyList().get(i).getKeyId());
            vo.setKey_name(addBo.getKeyList().get(i).getKeyName());
            vo.setValue_id(addBo.getValueList().get(i).getId());
            vo.setValue_name(addBo.getValueList().get(i).getValueName());
            skuSpuAttrList.add(vo);
        }

        String s = JSON.toJSONString(skuSpuAttrList);
        // 给spu的sku描述赋值
        sku.setSkuSpuattr(s);


        // 调用持久层接口添加数据
        int i = skuMapper.insertOne(sku);


        return i >= 1;

    }

    /**
     * 根据筛选条件查询出sku列表
     * @param searchBo
     * @param pageBo
     * @return
     */
    @Transactional
    @Override
    public List<Sku> listBySearchBo(SkuSearchBo searchBo, PageBo pageBo) {

        // 先查询出满足条件的记录数
        Long count = skuMapper.getCount(searchBo);
        pageBo.setResultCount(count);

        // 查询出满足条件的sku列表
        List<Sku> skus = skuMapper.listBySearchBo(searchBo, pageBo);
        return skus;
    }
}
