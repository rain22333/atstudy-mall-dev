package com.atstudy.entity.bo;

import lombok.Data;

@Data
public class SpuSearchBo {
    private Long spuId;
    private String spuBrandId;
    private String spuName;
    private Byte spuStatus;
    private Integer cateId;
}
