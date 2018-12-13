package cn.com.onlinetool.mapper;

import cn.com.onlinetool.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User>{
    List<User> findAll();
}