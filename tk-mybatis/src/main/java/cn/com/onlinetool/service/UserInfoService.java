package cn.com.onlinetool.service;

import cn.com.onlinetool.common.base.BaseService;
import cn.com.onlinetool.dto.UserInfoDTO;
import cn.com.onlinetool.entity.UserInfo;
import cn.com.onlinetool.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author choice
 * @description:
 * @date 2018/11/9 下午2:42
 *
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class UserInfoService extends BaseService<UserInfoMapper, UserInfo, UserInfoDTO>{
}
