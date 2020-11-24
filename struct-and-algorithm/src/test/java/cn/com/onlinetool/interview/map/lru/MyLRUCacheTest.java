package cn.com.onlinetool.interview.map.lru;


import cn.com.onlinetool.map.lru.MyLRUCache;

public class MyLRUCacheTest {
    public static void main(String[] args) {

        MyLRUCache<String,String> cache = new MyLRUCache(3);
        System.out.println("初始化");
        cache.put("A","a");
        cache.put("B","b");
        cache.put("C","c");
        cache.forEach();

        System.out.println("执行get操作");
        cache.get("A");
        cache.get("C");
        cache.forEach();

        System.out.println("执行put操作");
        cache.put("D","d");
        cache.forEach();


    }
}
