package cn.com.onlinetool.service;

import cn.com.onlinetool.common.base.BaseService;
import cn.com.onlinetool.dto.UserDTO;
import cn.com.onlinetool.entity.User;
import cn.com.onlinetool.mapper.UserMapper;
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
public class UserService extends BaseService<UserMapper, User, UserDTO> {
}
