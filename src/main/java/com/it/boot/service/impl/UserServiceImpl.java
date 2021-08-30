package com.it.boot.service.impl;

import com.it.boot.mapper.UserMapper;
import com.it.boot.pojo.User;
import com.it.boot.service.UserService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    Counter counter;

    public UserServiceImpl(MeterRegistry meterRegistry){
        counter = meterRegistry.counter("userService.getUserById.count");
    }

    @Override
    public User getUserById(int id) {
        counter.increment();
        User user = userMapper.getUser(id);

        return user;
    }
}
