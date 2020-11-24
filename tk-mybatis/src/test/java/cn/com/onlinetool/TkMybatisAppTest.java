package cn.com.onlinetool;

import static org.junit.Assert.assertTrue;
import cn.com.onlinetool.mybatis.entity.User;
import cn.com.onlinetool.mybatis.entity.UserInfo;
import cn.com.onlinetool.mybatis.mapper.UserInfoMapper;
import cn.com.onlinetool.mybatis.mapper.UserMapper;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Unit test for simple App.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TkMybatisAppTest
{
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    UserMapper userMapper;
    /**
     * Rigorous TestDto :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test(){
        List<User> users = userMapper.selectAll();
        System.out.println(JSON.toJSONString(users));
    }

    @Test
    public void test1(){
        List<UserInfo> userInfos = userInfoMapper.selectAll();
        System.out.println(JSON.toJSONString(userInfos));
    }
}
