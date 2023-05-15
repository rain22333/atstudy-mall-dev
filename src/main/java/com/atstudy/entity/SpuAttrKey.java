package com.atstudy.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 属性键实体类
 */
@Data
public class SpuAttrKey {

    private String keyId;
    private String keyName;
    private Byte keyIssku;
    private Byte keyIshigh;
    private LocalDateTime createtime;
    private LocalDateTime updatetime;

    private List<SpuAttrValue> valueList = new ArrayList<>();

}
