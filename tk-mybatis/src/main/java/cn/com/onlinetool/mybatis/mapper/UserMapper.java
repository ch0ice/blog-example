package cn.com.onlinetool.mybatis.mapper;

import cn.com.onlinetool.mybatis.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User>{
    List<User> findAll();
}