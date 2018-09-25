package cn.com.onlinetool.controller;

import cn.com.onlinetool.properties.DataSourceProperties;
import cn.com.onlinetool.service.TestRedisClusterService;
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
    @Autowired
    private TestRedisClusterService testRedisClusterService;

    @GetMapping("testRedis")
    public String testRedis(){
//        testRedisClusterService.insertTestDataByJedisCluster(1000000);

        if( 1 == DataSourceProperties.redisProperties.getType()){
            //测试 集群单命令管道 和普通单命令
            testRedisClusterService.testHGetAllByJedisCluster();
            testRedisClusterService.testHGetAllByJedisClusterPipelined();
            testRedisClusterService.testHGetByJedisCluster();
            testRedisClusterService.testHGetByJedisClusterPipelined();
        }else{
            //测试 单点或哨兵单命令管道 和普通单命令
            testRedisService.testHGetByRedisTemp();
            testRedisService.testHGetByJedis();
            testRedisService.testHGetAllByRedisTemp();
            testRedisService.testHGetAllByJedis();
            testRedisService.testHGetAllByRedisTempPiperlined();
            testRedisService.testHGetAllByJedisPipelined();
        }

        return "Hello Test Redis Is Single Command，This Example Use Jedis 2.9，Spring Boot 1.5.4";
    }
}
