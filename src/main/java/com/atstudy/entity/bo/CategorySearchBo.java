package com.atstudy.entity.bo;

import lombok.Data;

/**
 * 商品分类 搜索 业务模型类
 */
@Data
public class CategorySearchBo {
    private Integer cateId;
    private String cateName;
    private Integer cateParentid;
}
