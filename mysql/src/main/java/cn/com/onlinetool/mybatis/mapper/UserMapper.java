package cn.com.onlinetool.mybatis.mapper;

import cn.com.onlinetool.mybatis.entity.User;

public interface UserMapper {
    User selectByPrimaryKey(Integer id);
}