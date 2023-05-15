package com.atstudy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商品spu实体模型类
 */
@Data
public class Spu {

    @TableId
    private Long spuId;
    private String spuName;
    private String spuTitle;
    private String spuIntroduction;
    private String spuUnit;
    private Byte spuSketchtype;
    private String spuSketch;
    private String spuSpecs;
    private Byte spuSkuType;
    private Byte spuStatus;
    private LocalDateTime createtime;
    private LocalDateTime updatetime;
    private String spuBrandId;


    // 商品所属品牌
    private Brand brand;
}