package cn.com.onlinetool.bean;


/**
 * @author choice
 * @description:
 * @date 2018/8/31 13:57
 */
public class RedisBean {
    /** 单点 */
    private String singleHost;
    private int singlePort;
    /** 集群 */
    private String clusterHost;
    private String clusterPort;
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

    public String getClusterHost() {
        return clusterHost;
    }

    public void setClusterHost(String clusterHost) {
        this.clusterHost = clusterHost;
    }

    public String getClusterPort() {
        return clusterPort;
    }

    public void setClusterPort(String clusterPort) {
        this.clusterPort = clusterPort;
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
}
