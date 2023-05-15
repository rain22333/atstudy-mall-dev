package com.atstudy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Permission {

    @TableId
    private Integer permissionId;
    private String permissionName;
    private Integer pid;

}
