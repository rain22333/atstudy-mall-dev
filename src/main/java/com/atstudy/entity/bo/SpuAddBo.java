package com.atstudy.entity.bo;

import lombok.Data;

@Data
public class SpuAddBo {

    private String spuName;
    private String spuTitle;
    private String spuIntroduction;
    private Byte spuSkuType;
    private Byte spuStatus;
    private String spuBrandId;
    private Integer cateId;
}
