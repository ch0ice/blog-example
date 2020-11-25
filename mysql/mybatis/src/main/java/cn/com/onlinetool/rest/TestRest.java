package cn.com.onlinetool.rest;

import cn.com.onlinetool.entity.User;
import cn.com.onlinetool.mapper.UserMapper;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author choice
 * @description:
 * @date 2018/11/9 下午3:59
 *
 */
@RestController
@RequestMapping("/api")
public class TestRest {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/test")
    public String test(){
        User user = userMapper.selectByPrimaryKey(123);
        return JSON.toJSONString(user);
    }
}
