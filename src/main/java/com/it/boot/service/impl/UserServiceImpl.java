package com.it.boot.service.impl;

import com.it.boot.mapper.UserMapper;
import com.it.boot.pojo.User;
import com.it.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(int id) {
        User user = userMapper.getUser(id);

        return user;
    }
}
