package cn.com.onlinetool.mapper;

import cn.com.onlinetool.entity.User;

public interface UserMapper {
    User selectByPrimaryKey(Integer id);
}