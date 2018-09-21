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
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author choice
 * @description:
 * @date 2018/9/18 下午3:22
 *
 */
@Service
public class TestRedisService {
    private static Logger logger = LoggerFactory.getLogger(DataSourceProperties.class);
    @Resource
    private RedisTemplate<byte[],byte[]> redisTemplate;

    private static JedisUtil jedisUtil = JedisUtil.getInstance();
    private static final String redisKey = "TEST_REDIS";

    /**
     * 插入测试数据
     * @param count
     * @return 插入的条数
     */
    public long insertData(long count){
        try{
            for (int i = 1; i <= count; i++){
                System.out.println(i);
                redisTemplate.opsForHash().put("TEST_REDIS".getBytes("UTF-8"),(i + ""),"test redis");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }


    /**
     * @author choice
     * @return 运行时长
     */
    public long testRedisTempHGETALL(){
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        long startTime = System.currentTimeMillis();
        try {
            Map<Object,Object> result = redisTemplate.opsForHash().entries(redisKey.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            logger.info("execute hGetAll by redisTemplate error",e);
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        logger.info("hGetAll by redisTemplate：" + runTime + "ms");
        return runTime;
    }

    /**
     * @author choice
     * @return 运行时长
     */
    public long testRedisTempByPiperlinedHGETALL(){
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        long startTime = System.currentTimeMillis();
        List<Object> result = redisTemplate.executePipelined(new RedisCallback<Map<byte[],byte[]>>() {
            @Override
            public Map<byte[],byte[]> doInRedis(RedisConnection conn)
                throws DataAccessException {
                Map<byte[],byte[]> map = null;
                try {
                    map = conn.hGetAll(redisKey.getBytes("UTF-8"));
                } catch (Exception e) {
                    logger.info("hGetAll by redisTemplate is pipelined error",e);
                    e.printStackTrace();
                }
                return map;
            }
        });
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        logger.info("hGetAll by redisTemplate is pipelined run time：" + runTime + "ms");
        return runTime;
    }

    /**
     * @author choice
     * @return 运行时长
     */
    public long testJedisHGETALL(){
        long startTime = System.currentTimeMillis();
        Map<byte[],byte[]> hResult = jedisUtil.hgetAll(redisKey.getBytes());
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        logger.info("hGetAll by jedis run time：" + runTime + "ms");
        return runTime;
    }

    /**
     * @author choice
     * @return 运行时长
     */
    public long testJedisByPipelinedHGETALL(){
        long startTime = System.currentTimeMillis();
        List<String> redisKeys = new ArrayList<>();
        redisKeys.add(redisKey);
        Map<String, Map<byte[],byte[]>> pResult = jedisUtil.hgetAllByPipelined(redisKeys);
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        logger.info("hGetAll by jedis is pipelined run time：" + runTime + "ms");
        return runTime;
    }

    /**
     * @author choice
     * @return 运行时长
     */
    public long testJedisByClusterPipelinedHGETALL(){
        long startTime = System.currentTimeMillis();
        Map<String, Map<byte[],byte[]>> pResult = jedisUtil.hgetAllByClusterPipelined(redisKey);
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        logger.info("hGetAll by jedis is ClusterPipelined run time：" + runTime + "ms");
        return runTime;
    }


}
