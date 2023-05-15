package com.atstudy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商品sku实体模型类
 */
@Data
public class Sku {

    @TableId
    private Long skuId;
    private Long skuSpuId;
    private String skuName;
    private String skuSpuattr;
    private double skuPrice;
    private double skuOriginalprice;
    private double skuCostprice;
    private Integer skuQuantity;
    private Integer sortno;
    private LocalDateTime createtime;
    private LocalDateTime updatetime;

}
