package com.luojia.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class TestRedisDataType {
    
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.0.110", 6379);
        // key
        System.out.println("***********String数据类型**********");
        Set<String> keys = jedis.keys("*");
        for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            System.out.println(key);
        }
        
        System.out.println("jedis.exists(\"k2\")\"====>" + jedis.exists("k2"));
        System.out.println("jedis.ttl(\"k1\")" + jedis.ttl("k1"));
        
        // String数据类型
        System.out.println(jedis.get("k1"));
        jedis.set("k4", "v4_redis");
        System.out.println("----------------------");
        // 批量插入
        jedis.mset("str1", "v1","str2", "v2","str3", "v3");
        // 批量查询
        List<String> mget = jedis.mget("str1", "str2", "str3");
        // 打印所有需要查询的结果
        mget.forEach(System.out::println);
        System.out.println("----------------------");
        
        // list数据类型
        System.out.println("***********List据类型**********");
        jedis.lpush("mylist", "v1","v2","v3","v4","v5");
        List<String> lrange = jedis.lrange("mylist", 0, -1);
        lrange.forEach(System.out::println);
        
        // set数据类型
        System.out.println("***********Set数据类型**********");
        jedis.sadd("orders", "jd001");
        jedis.sadd("orders", "jd002");
        jedis.sadd("orders", "jd003");
        Set<String> smembers = jedis.smembers("orders");
        for (Iterator iterator = smembers.iterator(); iterator.hasNext();) {
            String next = (String) iterator.next();
            System.out.println(next);
        }
        System.out.println("jedis.sismember(\"orders\", \"jd001\")" + jedis.sismember("orders", "jd001"));
        
        // hash数据类型
        System.out.println("***********hash数据类型**********");
        jedis.hset("hash2", "userName", "lisi");
        System.out.println(jedis.hget("hash2", "userName"));
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("telphone", "13695438462");
        map.put("address", "wuhan");
        map.put("email", "abc@163.com");
        jedis.hmset("hash2", map);
        List<String> hmget = jedis.hmget("hash2", "telphone", "email");
        hmget.forEach(System.out::println);
        
        // zset数据类型
        System.out.println("***********zset数据类型**********");
        jedis.zadd("zset01", 60d, "v1");
        jedis.zadd("zset01", 70d, "v2");
        jedis.zadd("zset01", 80d, "v3");
        jedis.zadd("zset01", 90d, "v4");
        Set<String> zrange = jedis.zrange("zset01", 0, -1);
        zrange.forEach(System.out::println);
        
    }

}
