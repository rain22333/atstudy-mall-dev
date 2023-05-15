package com.atstudy.entity.bo;

import lombok.Data;

/**
 * sku 搜索业务模型类
 */
@Data
public class SkuSearchBo {
    private Long skuId;
    private Long skuSpuId;
    private String skuName;

    private double maxPrice;
    private double minPrice;

    private Integer maxSkuQuantity;
    private Integer minSkuQuantity;

    private Integer sortno;

}
