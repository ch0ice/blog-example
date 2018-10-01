package cn.com.onlinetool.properties;

import cn.com.onlinetool.bean.RedisBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 数据源接入配置文件
 * @author liujk@mapbar.com;
 * @create 2018-03-27 10:58
 */
public class DataSourceProperties {
    private static Logger logger = LoggerFactory.getLogger(DataSourceProperties.class);
    public static RedisBean redisProperties = new RedisBean();


    static {
        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = DataSourceProperties.class.getClassLoader().getResourceAsStream("jedisConfig.properties");
        // 使用properties对象加载输入流
        try {
            properties.load(in);
        } catch (IOException e) {
            logger.info("读取配置文件失败");
            e.printStackTrace();
        }
        redisProperties.setSingleHost(properties.getProperty("redis.single.host"));
        redisProperties.setSinglePort(Integer.parseInt(properties.getProperty("redis.single.port")));
        redisProperties.setClusterNode(properties.getProperty("redis.cluster.node"));
        redisProperties.setSentinelMaster(properties.getProperty("redis.sentinel.master"));
        redisProperties.setSentinelNode(properties.getProperty("redis.sentinel.node"));

        redisProperties.setPass(properties.getProperty("redis.pass"));
        redisProperties.setTimeout(Integer.parseInt(properties.getProperty("redis.timeout")));
        redisProperties.setMinIdle(Integer.parseInt(properties.getProperty("redis.minIdle")));
        redisProperties.setMaxIdle(Integer.parseInt(properties.getProperty("redis.maxIdle")));
        redisProperties.setMaxTotal(Integer.parseInt(properties.getProperty("redis.maxTotal")));
        redisProperties.setMaxWaitMillis(Integer.parseInt(properties.getProperty("redis.maxWaitMillis")));
        redisProperties.setTestOnBorrow(Boolean.parseBoolean(properties.getProperty("redis.testOnBorrow")));
        redisProperties.setType(Integer.parseInt(properties.getProperty("redis.type")));


    }


}
