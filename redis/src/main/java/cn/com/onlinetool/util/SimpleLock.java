package cn.com.onlinetool.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.UUID;

/**
 * @author choice
 * @description: 通过 redis 实现一个简单的 分布式锁
 * @date 2018-12-13 15:43
 *
 */
public class SimpleLock {

    private final String LOCK_NAME = "lock";
    Jedis conn = new Jedis("192.168.144.33",6388);


    /**
     * 获得锁重入锁(递归锁)
     * 锁 分为 重入锁 和 非重入锁
     * @return
     */
    public String accquireLock(int timeout){
        String uuid = UUID.randomUUID().toString();
        long end = System.currentTimeMillis() + timeout;
        while (System.currentTimeMillis() < end){
            //如果获取锁成功
            if(conn.setnx(LOCK_NAME,uuid).intValue() == 1){
                //增加key过期时间
                conn.expire(LOCK_NAME,timeout);
                return uuid;
            }
            //setnx 为 原子操作，但是expire不是，所以要单独判断
            if(conn.ttl(LOCK_NAME) == -1 ){
                //增加key过期时间
                conn.expire(LOCK_NAME,timeout);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 释放锁
     * @param uuid
     * @return
     */
    public boolean releaseLock(String uuid){
        while (true){
            //监控 key 防止值发生变化
            conn.watch(LOCK_NAME);
            if(uuid.equals(conn.get(LOCK_NAME))){
                Transaction transaction = conn.multi();
                transaction.del(LOCK_NAME);
                if(transaction.exec() == null){
                    continue;
                }
                return true;
            }
            conn.unwatch();
            break;
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleLock simpleLock = new SimpleLock();
        for (int i = 0; i < 10; i++){
            String uuid = simpleLock.accquireLock(1000000);
            if(uuid != null){
                System.out.println(i + "->获得锁成功");
            }else {
                System.out.println("获得锁失败");
            }
            break;
        }
    }
}
