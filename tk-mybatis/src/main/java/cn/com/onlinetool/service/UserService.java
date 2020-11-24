package cn.com.onlinetool.service;

import cn.com.onlinetool.common.base.BaseService;
import cn.com.onlinetool.mybatis.entity.User;
import cn.com.onlinetool.mybatis.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author choice
 * @description:
 * @date 2018/11/9 下午2:42
 *
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class UserService extends BaseService<UserMapper, User> {


    public User getUser(String id){
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("id",id);
        List<User> users = mapper.selectByExample(example);
        return users.size() > 0 ? users.get(0) : null;
    }

    public List<User> getUsers(){
        return mapper.findAll();
    }
}
