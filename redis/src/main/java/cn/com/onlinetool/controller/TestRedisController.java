package cn.com.onlinetool.controller;

import cn.com.onlinetool.service.TestRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author choice
 * @description:
 * @date 2018/9/18 下午3:32
 *
 */
@RestController
public class TestRedisController {
    @Autowired
    private TestRedisService testRedisService;

    @GetMapping("testRedis")
    public String testRedis(){
//        testRedisService.insertData(1000000);
        testRedisService.testJedisHGETALL();
        testRedisService.testJedisByPipelinedHGETALL();
        testRedisService.testRedisTempHGETALL();
        testRedisService.testRedisTempByPiperlinedHGETALL();
        return "Hello Test Redis Is Single Command，This Example Use Jedis 2.9，Spring Boot 1.5.4";
    }
}
