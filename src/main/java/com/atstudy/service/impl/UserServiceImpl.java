package com.atstudy.service.impl;

import com.atstudy.entity.User;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.entity.bo.UserSearchBo;
import com.atstudy.mapper.UserMapper;
import com.atstudy.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public List<User> listUserBySearchBo(UserSearchBo searchBo, PageBo pageBo) {
        // 查询出满足条件的总记录数，赋值给pgeBo
        int count = userMapper.getCount(searchBo);
        pageBo.setResultCount((long) count);

        // 查询满足条件的客户信息
        List<User> users = userMapper.listBySearchBo(searchBo, pageBo);
        return users;
    }
}
