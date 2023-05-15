package com.atstudy.entity.vo;

import lombok.Data;

/**
 * 可以判断某个角色是否拥有耨个权限  视图模型类
 */
@Data
public class PermissionWithRoleVo {

    private Integer permissionId;
    private String permissionName;
    private Integer pid;
    private Integer roleId;
}
