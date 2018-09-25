package cn.com.onlinetool.util;

import cn.com.onlinetool.properties.DataSourceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisClusterException;
import redis.clients.util.JedisClusterCRC16;

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

    private static Logger logger = LoggerFactory.getLogger(JedisUtil.class);

    /** 单点 */
    private static JedisPool jedisPool;
    /** 集群-哨兵 */
    private static JedisSentinelPool jedisSentinelPool;
    /** 集群 */
    private static JedisCluster jedisCluster;
    /** 用于缓存连接 */
    private static Map<String, JedisPool> nodeMap;
    /** 保存每个主机对应的redis内存槽 */
    private static TreeMap<Long, String> slotHostMap;
    /** 工具类对象 */
    private static final JedisUtil JEDIS_UTIL = new JedisUtil();

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
            String[] hostArray = DataSourceProperties.redisProperties.getClusterNode().split(",");
            // 添加节点
            for(int i = 0; i < hostArray.length; i++){
                String host = hostArray[i].split(":")[0];
                Integer port = Integer.parseInt(hostArray[i].split(":")[1]);
                hostAndPortsSet.add(new HostAndPort(host,port));
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


    /**
     * @author choice
     * 获取JedisUtil实例
     * 集群模式请直接获取JedisCluste使用，否则和回增加io消耗
     * @return
     */
    public static JedisUtil getInstance() {
        return JEDIS_UTIL;
    }

    /**
     * 获取集群实例
     * @return
     */
    public static JedisCluster getClusterInstance(){
        return jedisCluster;
    }

    /**
     * @author choice
     * 从jedis连接池中获取获取jedis对象
     * @return Jedis 连接实例对象
     */
    private Jedis getJedis() {
        if(2 == DataSourceProperties.redisProperties.getType()){
            return jedisSentinelPool.getResource();
        }
        return jedisPool.getResource();
    }

    /**
     * @author choice
     * 销毁连接池 和 连接池中的连接
     */
    public void destroy() {
        if(null != jedisPool){
            jedisPool.destroy();
        }
    }

    /**
     * 添加zset
     * @author choice
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
     * @author choice
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
     * @author choice
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
     * @author choice
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
     * @author choice
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
     * 删除 key
     * @author choice
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
     * @author choice
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


    /**
     * 删除指定db的key
     * @author choice
     * @param dbIndex
     * @param key
     * @throws Exception
     */
    public void deleteByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = getJedis();
        jedis.select(dbIndex);
        jedis.del(key);
        jedis.close();

    }


    /**
     * 获取总数量
     * @author choice
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
     * @author choice
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
     * @author choice
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
     * @author choice
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
     * @author choice
     * @param key
     */
    public void persist(String key) {
        Jedis jedis = getJedis();
        jedis.persist(key);
        jedis.close();
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
        long result = jedis.hset(key,field,value);
        jedis.close();
        return result;
    }

    /**
     * 插入hash
     * @param key
     * @param field
     * @param value
     * @return 0失败 1成功
     */
    public long hset(String key,String field,String value){
        Jedis jedis = getJedis();
        long result = jedis.hset(key,field,value);
        jedis.close();
        return result;
    }

    /**
     * hgetAll
     * @author choice
     * @param key
     */
    public byte[] hGet(byte[] key,byte[] field) {
        Jedis jedis = getJedis();
        byte[] hResult = null;
        hResult = jedis.hget(key,field);
        jedis.close();
        return hResult;
    }

    /**
     * hgetAll
     * @author choice
     * @param key
     */
    public String hGet(String key,String field) {
        Jedis jedis = getJedis();
        String hResult = jedis.hget(key,field);
        jedis.close();
        return hResult;
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
     * hgetAll
     * @author choice
     * @param keys
     */
    public Map<String, Map<byte[],byte[]>> hgetAllByPipelined(List<String> keys){
        Jedis jedis = getJedis();
        Pipeline pipeline = jedis.pipelined();
        Map<String, Response<Map<byte[],byte[]>>> responseMap = new HashMap<>();
        for (String field : keys) {
            try {
                responseMap.put(field, pipeline.hgetAll(field.getBytes("UTF-8")));
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
     * hGet by cluster pipelined
     * @author choice
     * @param key
     */
    public Map<String, byte[]> hGetByClusterPipelined(String key,List<String> fields){
        initJedisNodeMap();
        Jedis jedis = getJedis(key);
        Pipeline pipeline = jedis.pipelined();
        Map<String, Response<byte[]>> responseMap = new HashMap<>();
        for (String field : fields) {
            responseMap.put(field, pipeline.hget(key.getBytes(),field.getBytes()));
        }
        pipeline.sync();
        Map<String, byte[]> map = new HashMap<>();
        for (Map.Entry<String, Response<byte[]>> entry : responseMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue().get());
        }
        jedis.close();
        return map;
    }

    /**
     * hgetAll by cluster pipelined
     * @author choice
     * @param key
     */
    public Map<String, Map<byte[],byte[]>> hgetAllByClusterPipelined(String key){
        initJedisNodeMap();
        Jedis jedis = getJedis(key);
        Pipeline pipeline = jedis.pipelined();
        Map<String, Response<Map<byte[],byte[]>>> responseMap = new HashMap<>();
//        for (String field : fields) {
            try {
                responseMap.put(key, pipeline.hgetAll(key.getBytes("UTF-8")));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
//        }
        pipeline.sync();
        Map<String, Map<byte[],byte[]>> map = new HashMap<>();
        for (Map.Entry<String, Response<Map<byte[],byte[]>>> entry : responseMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue().get());
        }
        jedis.close();
        return map;
    }

    /**
     * 按键获取插槽，按位获取主机，按主机获取JedisPool，按位获取Jedis
     * @author choice
     * @param key
     * @return
     */
    public Jedis getJedis(String key) {
        int slot = JedisClusterCRC16.getSlot(key);
        Map.Entry<Long, String> entry = slotHostMap.lowerEntry(Long.valueOf(slot));
        return nodeMap.get(entry.getValue()).getResource();
    }

    /**
     * @author choice
     * 初始化 jedisNode
     * 获取nodeMap <String,JedisPool>，关键节点为主机。
     * 从它的任意主机获取插槽分布信息。
     */
    public void initJedisNodeMap() {
        try {
            nodeMap = jedisCluster.getClusterNodes();
            String anyHost = nodeMap.keySet().iterator().next();
            slotHostMap = getSlotHostMap(anyHost);
        }catch (JedisClusterException e){
            logger.error(e.getMessage());
        }
    }

    /**
     * @author choice
     * 将槽位分布保存为TreeMap
     * @param anyHostAndPortStr
     * @return
     */
    public static TreeMap<Long, String> getSlotHostMap(String anyHostAndPortStr) {
        TreeMap<Long, String> tree = new TreeMap<>();
        String parts[] = anyHostAndPortStr.split(":");
        HostAndPort anyHostAndPort = new HostAndPort(parts[0], Integer.parseInt(parts[1]));
        try (Jedis jedis = new Jedis(anyHostAndPort.getHost(), anyHostAndPort.getPort())) {
            List<Object> list = jedis.clusterSlots();
            for (Object object : list) {
                List<Object> list1 = (List<Object>) object;
                List<Object> master = (List<Object>) list1.get(2);
                String hostAndPort = new String((byte[]) master.get(0)) + ":" + master.get(1);
                tree.put((Long) list1.get(0), hostAndPort);
                tree.put((Long) list1.get(1), hostAndPort);
            }
        }
        return tree;
    }


}
