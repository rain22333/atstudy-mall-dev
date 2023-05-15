package com.atstudy.service;

import com.atstudy.entity.User;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.entity.bo.UserSearchBo;

import java.util.List;

public interface UserService {

    /**
     * 根据筛选条件查询客户信息
     * @param searchBo
     * @param pageBo
     * @return
     */
    List<User> listUserBySearchBo(UserSearchBo searchBo, PageBo pageBo);
}
