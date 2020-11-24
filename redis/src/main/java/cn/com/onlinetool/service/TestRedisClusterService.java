package cn.com.onlinetool.service;

import cn.com.onlinetool.properties.DataSourceProperties;
import cn.com.onlinetool.utils.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author choice
 * @description: redis 集群模式 测试
 * @date 2018/9/25 下午3:37
 *
 */
@Service
public class TestRedisClusterService {

    private static Logger logger = LoggerFactory.getLogger(DataSourceProperties.class);
    @Resource
    private RedisTemplate<byte[],byte[]> redisTemplate;

    private static JedisUtil jedisUtil = JedisUtil.getInstance();
    private static JedisCluster jedisCluster = JedisUtil.getClusterInstance();
    private static final String REDIS_KEY = "TEST_REDIS";
    private static final int KEY_COUNT = 100;



    /**
     * 插入测试数据
     * @param count
     * @return 插入的条数
     */
    public long insertTestDataByJedisCluster(long count){
        try{
            for (int i = 1; i <= count; i++){
                System.out.println(i);
                jedisCluster.hset(REDIS_KEY.getBytes(),(i + "").getBytes(),"test redis".getBytes());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }


    /**
     * 测试jedisCluster hGet
     * @author choice
     * @return 运行时长
     */
    public long testHGetByJedisCluster(){
        long startTime = System.currentTimeMillis();
        List<byte[]> result = new ArrayList<>();
        for(int i = 0; i < KEY_COUNT; i++){
            result.add(jedisCluster.hget(REDIS_KEY.getBytes(),(i + "").getBytes()));
        }
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        logger.info("hGet by jedisCluster run time：" + runTime + "ms");
        return runTime;
    }

    /**
     * 测试jedisCluster hGetAll
     * @author choice
     * @return 运行时长
     */
    public long testHGetAllByJedisCluster(){
        long startTime = System.currentTimeMillis();
        Map<byte[],byte[]> result = jedisCluster.hgetAll(REDIS_KEY.getBytes());
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        logger.info("hGetAll by jedisCluster run time：" + runTime + "ms");
        return runTime;
    }



    /**
     * 测试jedisCluster hGet
     * @author choice
     * @return 运行时长
     */
    public long testHGetByJedisClusterPipelined(){
        long startTime = System.currentTimeMillis();
        List<String> hkeys = new ArrayList<>();
        for(int i = 0; i < KEY_COUNT; i++){
            hkeys.add(i + "");
        }
        Map<String, byte[]> result = jedisUtil.hGetByClusterPipelined(REDIS_KEY,hkeys);
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        logger.info("hGet by jedisCluster is pipelined run time：" + runTime + "ms");
        return runTime;
    }


    /**
     * 测试jedisCluster hGetAll 管道
     * @author choice
     * @return 运行时长
     */
    public long testHGetAllByJedisClusterPipelined(){
        long startTime = System.currentTimeMillis();
        Map<String, Map<byte[],byte[]>> result = jedisUtil.hgetAllByClusterPipelined(REDIS_KEY);
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        logger.info("hGetAll by jedisCluster is Pipelined run time：" + runTime + "ms");
        return runTime;
    }





}
