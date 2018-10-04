package cn.com.onlinetool.service;

import cn.com.onlinetool.properties.DataSourceProperties;
import cn.com.onlinetool.util.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author choice
 * @description: redis 单点 or 哨兵模式 测试
 * @date 2018/9/18 下午3:22
 *
 */
@Service
public class TestRedisService {
    private static Logger logger = LoggerFactory.getLogger(DataSourceProperties.class);
    @Resource
    private RedisTemplate<byte[],byte[]> redisTemplate;

    private static JedisUtil jedisUtil = JedisUtil.getInstance();
    private static final String REDIS_KEY = "TEST_REDIS";
    private static final int KEY_COUNT = 100;

    /**
     * 插入测试数据
     * @param count
     * @return 插入的条数
     */
    public long insertTestDataByRedisTemp(long count){
        try{
            for (int i = 1; i <= count; i++){
                System.out.println(i);
                redisTemplate.opsForHash().put(REDIS_KEY.getBytes(),(i + ""),"test redis");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 测试redisTemplate hGet
     * @author choice
     * @return 运行时长
     */
    public long testHGetByRedisTemp(){
        long startTime = System.currentTimeMillis();
        List<Object> result = new ArrayList<>();
        for(int i = 0; i < KEY_COUNT; i++){
            result.add(redisTemplate.opsForHash().get(REDIS_KEY.getBytes(),i + ""));
        }
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        logger.info("hGet by redisTemplate run time：" + runTime + "ms");
        return runTime;
    }


    /**
     * 测试redisTemplate hGetAll
     * @author choice
     * @return 运行时长
     */
    public long testHGetAllByRedisTemp(){
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        long startTime = System.currentTimeMillis();
        Map<Object,Object> result = redisTemplate.opsForHash().entries(REDIS_KEY.getBytes());
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        logger.info("hGetAll by redisTemplate run time：" + runTime + "ms");
        return runTime;
    }

    /**
     * 测试redisTemplate hGetAll 管道
     * @author choice
     * @return 运行时长
     */
    public long testHGetAllByRedisTempPiperlined(){
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        long startTime = System.currentTimeMillis();
        List<Object> result = redisTemplate.executePipelined(new RedisCallback<Map<byte[],byte[]>>() {
            @Override
            public Map<byte[],byte[]> doInRedis(RedisConnection conn)
                throws DataAccessException {
                Map<byte[],byte[]> map = conn.hGetAll(REDIS_KEY.getBytes());
                return map;
            }
        });
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        logger.info("hGetAll by redisTemplatePipelined run time：" + runTime + "ms");
        return runTime;
    }


    /**
     * 测试jedis hGet
     * @author choice
     * @return 运行时长
     */
    public long testHGetByJedis(){
        long startTime = System.currentTimeMillis();
        List<byte[]> result = new ArrayList<>();
        for(int i = 0; i < KEY_COUNT; i++){
            result.add(jedisUtil.hGet(REDIS_KEY.getBytes(),(i + "").getBytes()));
        }
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        logger.info("hGet by jedis run time：" + runTime + "ms");
        return runTime;
    }

    /**
     * 测试jedis hGetAll
     * @author choice
     * @return 运行时长
     */
    public long testHGetAllByJedis(){
        long startTime = System.currentTimeMillis();
        Map<byte[],byte[]> result = jedisUtil.hgetAll(REDIS_KEY.getBytes());
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        logger.info("hGetAll by jedis run time：" + runTime + "ms");
        return runTime;
    }

    /**
     * 测试jedis hGetALl 管道
     * @author choice
     * @return 运行时长
     */
    public long testHGetAllByJedisPipelined(){
        long startTime = System.currentTimeMillis();
        Map<String, Map<byte[],byte[]>> result = jedisUtil.hgetAllByPipelined(REDIS_KEY);
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        logger.info("hGetAll by jedisPipelined run time：" + runTime + "ms");
        return runTime;
    }





}
