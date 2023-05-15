package com.atstudy.entity.bo;

import com.atstudy.entity.Category;
import com.atstudy.entity.SpuAttrValue;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AtteKeyAddBo {
    private String keyName;
    private Byte keyIssku;
    private Byte keyIshigh;

    // 属性值列表
    private List<SpuAttrValue> valueList = new ArrayList<>();

    // 分类id数组
    private Integer[] idList;
}
