package com.atstudy.entity.bo;

import lombok.Data;

@Data
public class BrandSearchBo {
    private String brandId;
    private String brandName;
    private Integer sortno;
    private Integer categoryId;
}
