package cn.com.onlinetool.bean;


import java.util.Objects;

/**
 * @author choice
 * @description:
 * @date 2018/8/31 13:57
 */
public class RedisBean {
    /** 单点 */
    private String singleHost;
    private int singlePort;
    /** 集群节点(ip:port,ip:port) */
    private String clusterNode;
    /** 哨兵 */
    private String sentinelMaster;
    private String sentinelNode;

    private String pass;
    /** 超时时间 */
    private int timeout;
    /** 最大空闲连接数 */
    private int maxIdle;
    /** 最小空闲连接数 */
    private int minIdle;
    /** 最大连接数 */
    private int maxTotal;
    /** 最大等待时间 */
    private int maxWaitMillis;
    private boolean testOnBorrow;
    private int type;


    public String getSingleHost() {
        return singleHost;
    }

    public void setSingleHost(String singleHost) {
        this.singleHost = singleHost;
    }

    public int getSinglePort() {
        return singlePort;
    }

    public void setSinglePort(int singlePort) {
        this.singlePort = singlePort;
    }

    public String getClusterNode() {
        return clusterNode;
    }

    public void setClusterNode(String clusterNode) {
        this.clusterNode = clusterNode;
    }

    public String getSentinelMaster() {
        return sentinelMaster;
    }

    public void setSentinelMaster(String sentinelMaster) {
        this.sentinelMaster = sentinelMaster;
    }

    public String getSentinelNode() {
        return sentinelNode;
    }

    public void setSentinelNode(String sentinelNode) {
        this.sentinelNode = sentinelNode;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RedisBean redisBean = (RedisBean) o;
        return singlePort == redisBean.singlePort &&
                timeout == redisBean.timeout &&
                maxIdle == redisBean.maxIdle &&
                minIdle == redisBean.minIdle &&
                maxTotal == redisBean.maxTotal &&
                maxWaitMillis == redisBean.maxWaitMillis &&
                testOnBorrow == redisBean.testOnBorrow &&
                type == redisBean.type &&
                Objects.equals(singleHost, redisBean.singleHost) &&
                Objects.equals(clusterNode, redisBean.clusterNode) &&
                Objects.equals(sentinelMaster, redisBean.sentinelMaster) &&
                Objects.equals(sentinelNode, redisBean.sentinelNode) &&
                Objects.equals(pass, redisBean.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(singleHost, singlePort, clusterNode, sentinelMaster, sentinelNode, pass, timeout, maxIdle, minIdle, maxTotal, maxWaitMillis, testOnBorrow, type);
    }
}
