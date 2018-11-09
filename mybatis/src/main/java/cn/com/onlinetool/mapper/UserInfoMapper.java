package cn.com.onlinetool.mapper;

import cn.com.onlinetool.entity.UserInfo;

public interface UserInfoMapper {
    UserInfo selectByPrimaryKey(Integer userid);
}