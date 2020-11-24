package cn.com.onlinetool.mybatis.mapper;

import cn.com.onlinetool.mybatis.entity.UserInfo;

public interface UserInfoMapper {
    UserInfo selectByPrimaryKey(Integer userid);
}