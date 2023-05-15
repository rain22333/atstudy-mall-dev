package com.atstudy.entity.bo;

import lombok.Data;

/**
 * 属性键 搜索 业务模型类
 */
@Data
public class SpuAttrKeySearchBo {

    private String keyId;
    private String keyName;
    private Byte keyIssku;
    private Byte keyIshigh;
}
