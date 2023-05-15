package com.atstudy.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 属性值 实体模型类
 */
@Data
public class SpuAttrValue {
    private Long id;
    private String valueName;
    private LocalDateTime createtime;
    private LocalDateTime updatetime;
    private String valueAttrKeyId;

}
