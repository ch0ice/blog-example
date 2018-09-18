package cn.com.onlinetool.util;

import cn.com.onlinetool.properties.DataSourceProperties;
import redis.clients.jedis.*;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author choice
 * @description:
 * @date 2018/8/31 13:57
 */
public class JedisUtil {



    private JedisUtil() {

    }

    private static JedisCluster jedisCluster;
    private static JedisPool jedisPool = null;
    private static JedisSentinelPool jedisSentinelPool = null;
    static {
        //单点
        if(0 == DataSourceProperties.redisProperties.getType()){
            // Jedis连接池配置
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(DataSourceProperties.redisProperties.getMaxTotal());
            config.setMaxIdle(DataSourceProperties.redisProperties.getMaxIdle());
            config.setMaxWaitMillis(DataSourceProperties.redisProperties.getMaxWaitMillis());
            config.setTestOnBorrow(DataSourceProperties.redisProperties.isTestOnBorrow());

            String host = DataSourceProperties.redisProperties.getSingleHost();
            int port = DataSourceProperties.redisProperties.getSinglePort();
            int timeout = DataSourceProperties.redisProperties.getTimeout();
            jedisPool = new JedisPool(config,host,port,timeout);
        }
        //集群
        else if(1 == DataSourceProperties.redisProperties.getType()){
            // 添加集群的服务节点Set集合
            Set<HostAndPort> hostAndPortsSet = new HashSet<HostAndPort>();
            String[] hostArray = DataSourceProperties.redisProperties.getClusterHost().split(",");
            String[] portArray = DataSourceProperties.redisProperties.getClusterPort().split(",");
            // 添加节点
            for(int i = 0; i < hostArray.length; i++){
                hostAndPortsSet.add(new HostAndPort(hostArray[i], Integer.parseInt(portArray[i])));
            }

            // Jedis连接池配置
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            int minIdle = DataSourceProperties.redisProperties.getMinIdle();
            int maxIdle = DataSourceProperties.redisProperties.getMaxIdle();
            int maxTotal = DataSourceProperties.redisProperties.getMaxTotal();
            int maxWaitMillis = DataSourceProperties.redisProperties.getMaxWaitMillis();
            boolean testOnBorrow = DataSourceProperties.redisProperties.isTestOnBorrow();

            jedisPoolConfig.setMinIdle(minIdle);
            jedisPoolConfig.setMaxIdle(maxIdle);
            jedisPoolConfig.setMaxTotal(maxTotal);
            jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
            jedisPoolConfig.setTestOnBorrow(testOnBorrow);
            jedisCluster = new JedisCluster(hostAndPortsSet, jedisPoolConfig);
        }
        //哨兵
        else if(2 == DataSourceProperties.redisProperties.getType()){
            Set<String> sentinels = new HashSet<>();
            String sentinelStr = DataSourceProperties.redisProperties.getSentinelNode();
            String[] sentinelArray = sentinelStr.split(",");
            for (String s : sentinelArray) {
                sentinels.add(s);
            }
            jedisSentinelPool = new JedisSentinelPool(DataSourceProperties.redisProperties.getSentinelMaster(),sentinels);
        }


    }
    private static final JedisUtil JEDIS_UTIL = new JedisUtil();
    /**
     * 获取JedisUtil实例
     *
     * @return
     */
    public static JedisUtil getInstance() {
        return JEDIS_UTIL;
    }
    /**
     * 从jedis连接池中获取获取jedis对象
     *
     * @return
     */
    private Jedis getJedis() {
        if(2 == DataSourceProperties.redisProperties.getType()){
            return jedisSentinelPool.getResource();
        }
        return jedisPool.getResource();
    }

    /**
     * 销毁连接池
     * @return
     */
    public void destroy() {
        if(null != jedisPool){
            jedisPool.destroy();
        }
    }



    /**
     * 添加sorted set
     *
     * @param key
     * @param value
     * @param score
     */
    public void zadd(String key, String value, double score) {
        Jedis jedis = getJedis();
        jedis.zadd(key, score, value);
        jedis.close();
    }

    /**
     * 返回指定位置的集合元素,0为第一个元素，-1为最后一个元素
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<byte[]> zrange(String key, int start, int end) {
        Jedis jedis = getJedis();
        Set<byte[]> set = jedis.zrange(key.getBytes(), start, end);
        jedis.close();
        return set;
    }

    /**
     * 获取给定区间的元素，原始按照权重由高到低排序
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrevrange(String key, int start, int end) {
        Jedis jedis = getJedis();
        Set<String> set = jedis.zrevrange(key, start, end);
        jedis.close();
        return set;
    }

    /**
     * 添加对应关系，如果对应关系已存在，则覆盖
     *
     * @param key
     * @param map 对应关系
     * @return 状态，成功返回OK
     */
    public String hmset(String key, Map<String, String> map) {
        Jedis jedis = getJedis();
        String s = jedis.hmset(key, map);
        jedis.close();
        return s;
    }

    /**
     * 向List头部追加记录
     *
     * @param key
     * @param value
     * @return 记录总数
     */
    private long rpush(byte[] key, byte[] value) {
        Jedis jedis = getJedis();
        long count = jedis.rpush(key, value);
        jedis.close();
        return count;
    }

    /**
     * 删除
     *
     * @param key
     * @return
     */
    public long del(String key) {
        Jedis jedis = getJedis();
        long s = jedis.del(key);
        jedis.close();
        return s;
    }

    /**
     * 从集合中删除成员
     * @param key
     * @param value
     * @return 返回1成功
     * */
    public long zrem(String key, String... value) {
        Jedis jedis = getJedis();
        long s = jedis.zrem(key, value);
        jedis.close();
        return s;
    }


    public void deleteByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = getJedis();
        jedis.select(dbIndex);
        jedis.del(key);
        jedis.close();

    }


    /**
     * 获取总数量
     * @param key
     * @return
     */
    public long zcard(String key) {
        Jedis jedis = getJedis();
        long count = jedis.zcard(key);
        jedis.close();
        return count;
    }

    /**
     * 是否存在KEY
     * @param key
     * @return
     */
    public boolean exists(String key) {
        Jedis jedis = getJedis();
        boolean exists = jedis.exists(key);
        jedis.close();
        return exists;
    }

    /**
     * 重命名KEY
     * @param oldKey
     * @param newKey
     * @return
     */
    public String rename(String oldKey, String newKey) {
        Jedis jedis = getJedis();
        String result = jedis.rename(oldKey, newKey);
        jedis.close();
        return result;
    }

    /**
     * 设置失效时间
     * @param key
     * @param seconds
     */
    public void expire(String key, int seconds) {
        Jedis jedis = getJedis();
        jedis.expire(key, seconds);
        jedis.close();
    }

    /**
     * 删除失效时间
     * @param key
     */
    public void persist(String key) {
        Jedis jedis = getJedis();
        jedis.persist(key);
        jedis.close();
    }


    /**
     * 返回指定key序列值
     * @param key
     * @return
     */
    public long incr(String key){
        Jedis jedis = getJedis();
        long l = jedis.incr(key);
        jedis.close();
        return l;
    }



    /**
     * hgetAll
     * @author choice
     * @param key
     */
    public Map<byte[],byte[]> hgetAll(byte[] key) {
        Jedis jedis = getJedis();
        Map<byte[],byte[]> hResult = jedis.hgetAll(key);
        jedis.close();
        return hResult;
    }

    /**
     * hgetAll
     * @author choice
     * @param key
     */
    public Map<String,String> hgetAll(String key) {
        Jedis jedis = getJedis();
        Map<String,String> hResult = jedis.hgetAll(key);
        jedis.close();
        return hResult;
    }


    /**
     * hgetAll
     * @author choice
     * @param keys
     */
    public Map<String, Map<byte[],byte[]>> hgetAllByPipelined(List<String> keys){
        Jedis jedis = getJedis();
        Pipeline pipeline = jedis.pipelined();
        Map<String, Response<Map<byte[],byte[]>>> responseMap = new HashMap<>();
        for (String key : keys) {
            try {
                responseMap.put(key, pipeline.hgetAll(key.getBytes("UTF-8")));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        pipeline.sync();
        Map<String, Map<byte[],byte[]>> map = new HashMap<>();
        for (Map.Entry<String, Response<Map<byte[],byte[]>>> entry : responseMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue().get());
        }
        jedis.close();
        return map;
    }

    /**
     * zrangeByScore
     * @author choice
     * @param keys
     */
    public Map<String, Set<byte[]>> zrangeByScoreByPipelined(List<String> keys,long sTime,long eTime){
        Jedis jedis = getJedis();
        Pipeline pipeline = jedis.pipelined();
        Map<String, Response<Set<byte[]>>> responseMap = new HashMap<>();
        for (String key : keys) {
            try {
                responseMap.put(key, pipeline.zrangeByScore(key.getBytes("UTF-8"),sTime,eTime));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        pipeline.sync();
        Map<String, Set<byte[]>> map = new HashMap<>();
        for (Map.Entry<String, Response<Set<byte[]>>> entry : responseMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue().get());
        }
        jedis.close();
        return map;
    }

    /**
     * 插入hash
     * @param key
     * @param field
     * @param value
     * @return 0失败 1成功
     */
    public long hset(byte[] key,byte[] field,byte[] value){
        Jedis jedis = getJedis();
        return jedis.hset(key,field,value);
    }
}
