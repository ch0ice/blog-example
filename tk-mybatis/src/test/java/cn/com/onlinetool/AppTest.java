package cn.com.onlinetool;

import static org.junit.Assert.assertTrue;
import cn.com.onlinetool.entity.User;
import cn.com.onlinetool.entity.UserInfo;
import cn.com.onlinetool.mapper.UserInfoMapper;
import cn.com.onlinetool.mapper.UserMapper;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest
{
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
        ApplicationContext context = new ClassPathXmlApplicationContext("application.properties");
        UserMapper userMapper = context.getBean(UserMapper.class);
        List<User> users = userMapper.selectAll();
        System.out.println(JSON.toJSONString(users));
    }

    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("application.properties");
        UserInfoMapper userInfoMapper = context.getBean(UserInfoMapper.class);
        List<UserInfo> userInfos = userInfoMapper.selectAll();
        System.out.println(JSON.toJSONString(userInfos));
    }
}
