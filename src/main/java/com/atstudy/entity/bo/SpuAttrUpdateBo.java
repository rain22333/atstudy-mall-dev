package com.atstudy.entity.bo;

import com.atstudy.entity.SpuAttrValue;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于接收属性键更新数据
 */
@Data
public class SpuAttrUpdateBo {
    private String keyId;
    private String keyName;
    private Byte keyIssku;
    private Byte keyIshigh;

    // 属性值列表
    private List<SpuAttrValue> valueList = new ArrayList<>();

    // 分类id数组
    private Integer[] idList;
}
