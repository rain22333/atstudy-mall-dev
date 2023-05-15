package com.atstudy.entity.bo;

import com.atstudy.entity.Role;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加员工的业务模型类
 */
@Data
public class AdminAddBo {

    private Integer adminId;
    private String adminName;
    private String adminPass;
    private String adminNickname;

    private List<Role> roleList = new ArrayList<>();
}
