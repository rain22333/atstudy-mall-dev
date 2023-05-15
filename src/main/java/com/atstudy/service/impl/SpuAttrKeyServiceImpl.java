package com.atstudy.service.impl;

import com.atstudy.entity.SpuAttrKey;
import com.atstudy.entity.SpuAttrValue;
import com.atstudy.entity.bo.AtteKeyAddBo;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.entity.bo.SpuAttrKeySearchBo;
import com.atstudy.entity.bo.SpuAttrUpdateBo;
import com.atstudy.mapper.SpuAttrKeyCategoryMapper;
import com.atstudy.mapper.SpuAttrKeyMapper;
import com.atstudy.mapper.SpuAttrValueMapper;
import com.atstudy.service.SpuAttrKeyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class SpuAttrKeyServiceImpl implements SpuAttrKeyService {


    @Resource
    private SpuAttrKeyMapper spuAttrKeyMapper;

    @Resource
    private SpuAttrValueMapper spuAttrValueMapper;


    @Resource
    private SpuAttrKeyCategoryMapper spuAttrKeyCategoryMapper;

    @Override
    public List<SpuAttrKey> getKeyListBySpuId(Long spuId) {
        return spuAttrKeyMapper.getKeyListBySpuId(spuId);
    }

    @Transactional
    @Override
    public boolean deleteOne(String keyId) {

        // 先删除属性键信息
        int i1 = spuAttrKeyMapper.deleteOneByKeyId(keyId);

        // 删除当前属性键拥有的属性值
        QueryWrapper<SpuAttrValue> wrapper = new QueryWrapper<>();
        wrapper.eq("value_attr_key_id",keyId);
        int i2 = spuAttrValueMapper.delete(wrapper);

        // 删除属性键-分类关系
        int i3 = spuAttrKeyCategoryMapper.deleteBatchByKeyId(keyId);

        return i1 >= 1 && i2 >= 1;
    }

    @Transactional
    @Override
    public boolean deleteBatchByIdList(String[] idList) {

        // 先删除属性键信息
        int i1 = spuAttrKeyMapper.deleteBatchByIdList(idList);

        // 删除这些属性键拥有的属性值
        int i2 = spuAttrValueMapper.deleteBatchByKeyIdList(idList);

        // 批量删除属性键-分类关系
        int i3 = spuAttrKeyCategoryMapper.deleteBatchByKeyIdList(idList);

        return i1 >= 1 && i2 >= 1;
    }

    @Transactional
    @Override
    public boolean updateByBo(SpuAttrUpdateBo updateBo) {

        // 先更新属性键
        // 创建对象并赋值用于更新
        SpuAttrKey spuAttrKey = new SpuAttrKey();
        spuAttrKey.setKeyId(updateBo.getKeyId());
        spuAttrKey.setKeyName(updateBo.getKeyName());
        spuAttrKey.setKeyIssku(updateBo.getKeyIssku());
        spuAttrKey.setKeyIshigh(updateBo.getKeyIshigh());
        LocalDateTime now = LocalDateTime.now();
        spuAttrKey.setUpdatetime(now);

        int i1 = spuAttrKeyMapper.updateOne(spuAttrKey);

        // 将原来的属性值全部删除，重新添加
        QueryWrapper<SpuAttrValue> wrapper = new QueryWrapper<>();
        wrapper.eq("value_attr_key_id",updateBo.getKeyId());
        int i2 = spuAttrValueMapper.delete(wrapper);

        // 重新添加属性值
        // 再往属性值数据表中添加数据
        // 遍历获取到的只有名字的属性值列表
        for (SpuAttrValue spuAttrValue : updateBo.getValueList()) {
            // 给属性值设置创建更新时间
            spuAttrValue.setCreatetime(now);
            spuAttrValue.setUpdatetime(now);
            // 给属性值设置所属的属性键id
            spuAttrValue.setValueAttrKeyId(updateBo.getKeyId());
        }
        // 往属性值数据表中添加数据
        int i3 = spuAttrValueMapper.insertBatchByList(updateBo.getValueList(), updateBo.getKeyId());

        // 删除原来的属性键-分类关系，再重新添加
        int i4 = spuAttrKeyCategoryMapper.deleteBatchByKeyId(updateBo.getKeyId());

        // 遍历得到的分类id
        for (Integer integer : updateBo.getIdList()) {
            // 判断这个id是不是等于-1
            if( integer != -1){
                // 添加属性键-分类关系
                int insert = spuAttrKeyCategoryMapper.insert(updateBo.getKeyId(), integer);
            }
        }

        return i1 >= 1 && i2 >= 1 && i3 >= 1;
    }

    @Override
    public List<Integer> listCateIdByKeyId(String keyId) {
        return spuAttrKeyCategoryMapper.listCateIdByKeyId(keyId);
    }

    @Override
    public SpuAttrKey getByKeyId(String keyId) {

        return spuAttrKeyMapper.getByKeyId(keyId);
    }

    @Transactional
    @Override
    public boolean insertOne(AtteKeyAddBo addBo) {

        // 先往属性键表中添加数据
        // 创建出spuAttrKey对象
        SpuAttrKey spuAttrKey = new SpuAttrKey();
        String keyId = UUID.randomUUID().toString();
        // 给属性键的id赋值
        spuAttrKey.setKeyId(keyId);
        // 给属性键的名称赋值
        spuAttrKey.setKeyName(addBo.getKeyName());
        // 给属性间的属性分类、等级赋值
        spuAttrKey.setKeyIssku(addBo.getKeyIssku());
        spuAttrKey.setKeyIshigh(addBo.getKeyIshigh());
        // 给属性键的创建更新时间赋值
        LocalDateTime now = LocalDateTime.now();
        spuAttrKey.setCreatetime(now);
        spuAttrKey.setUpdatetime(now);

        int i1 = spuAttrKeyMapper.insertOne(spuAttrKey);


        // 再往属性值数据表中添加数据
        // 遍历获取到的只有名字的属性值列表
        for (SpuAttrValue spuAttrValue : addBo.getValueList()) {
            // 给属性值设置创建更新时间
            spuAttrValue.setCreatetime(now);
            spuAttrValue.setUpdatetime(now);
            // 给属性值设置所属的属性键id
            spuAttrValue.setValueAttrKeyId(keyId);
        }
        // 往属性值数据表中添加数据
        int i2 = spuAttrValueMapper.insertBatchByList(addBo.getValueList(), keyId);

        // 遍历得到的分类id
        for (Integer integer : addBo.getIdList()) {
            // 判断这个id是不是等于-1
            if( integer != -1){
                // 添加属性键-分类关系
                int insert = spuAttrKeyCategoryMapper.insert(keyId, integer);
            }
        }


        return i1 >= 1 && i2 >= 1;
    }

    @Override
    public List<SpuAttrKey> listBySearchBo(SpuAttrKeySearchBo searchBo, PageBo pageBo) {

        // 先查询出满足条件的总记录数
        Long count = spuAttrKeyMapper.getCount(searchBo);
        // 给pageBo中的总记录数赋值，计算出总页数
        pageBo.setResultCount(count);
        // 再查询出满足条件的属性键列表
        List<SpuAttrKey> keyList = spuAttrKeyMapper.listBySearchBo(searchBo, pageBo);

        return keyList;
    }
}
