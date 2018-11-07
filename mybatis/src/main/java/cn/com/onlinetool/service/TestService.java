package cn.com.onlinetool.service;


import cn.com.onlinetool.entity.Test;
import cn.com.onlinetool.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    private TestMapper testMapper;
    public void testMapper(){
        Test test = new Test();
        test.setId(1);
    }
}
