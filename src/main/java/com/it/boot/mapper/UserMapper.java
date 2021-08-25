package com.it.boot.mapper;

import com.it.boot.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public User getUser(int id);
}
