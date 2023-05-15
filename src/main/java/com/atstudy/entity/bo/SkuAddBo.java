package com.atstudy.entity.bo;

import com.atstudy.entity.SpuAttrKey;
import com.atstudy.entity.SpuAttrValue;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SkuAddBo {

    private String skuName;
    private Double skuPrice;
    private Double skuOriginalprice;
    private Double skuCostprice;
    private Integer skuQuantity;
    private Long skuSpuId;

    private List<SpuAttrValue> valueList = new ArrayList<>();

    private List<SpuAttrKey> keyList = new ArrayList<>();

}
