package cn.com.onlinetool.interview.map.lru;

import cn.com.onlinetool.map.lru.LRUCache;

public class LRUCacheTest {
    public static void main(String[] args) {
        LRUCache<String,String> cache = new LRUCache(4);
        cache.put("A","a");
        cache.put("B","b");
        cache.put("C","c");
        cache.forEach((k,v)->{
            System.out.print(k + "：" + v + "  ");
        });
        System.out.println();
        cache.get("A");
        cache.get("C");

        cache.forEach((k,v)->{
            System.out.print(k + "：" + v + "  ");
        });
        System.out.println();

        cache.put("D","d");
        cache.forEach((k,v)->{
            System.out.print(k + "：" + v + "  ");
        });

    }
}
