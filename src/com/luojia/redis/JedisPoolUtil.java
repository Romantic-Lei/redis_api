package com.luojia.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
    private static volatile JedisPool jedisPool = null;
    
    private JedisPoolUtil() {}
    
    public static JedisPool getJedisPoolInstance() {
        if(null == jedisPool) {
            synchronized (JedisPoolUtil.class) {
               if(null == jedisPool) {
                   JedisPoolConfig poolConfig = new JedisPoolConfig();
                   // 最大连接数
                   poolConfig.setMaxActive(1000);
                   // 控制一个pool最多有多少个状态为idle(空闲)的jedis实例)
                   poolConfig.setMaxIdle(50);
                   // 设置等待时间
                   poolConfig.setMaxWait(100*1000);
                   // 获得一个jedis实例的时候是否检查连接可用性（ping()）；如果为true，则得到的jedis实例均是可用的
                   poolConfig.setTestOnBorrow(true);
                   
                   jedisPool = new JedisPool(poolConfig, "192.168.0.110", 6379);
               }
            }
        }
        return jedisPool;
    }
    
    public static void release(JedisPool jedisPool, Jedis jedis) {
        if(null != jedis) {
            jedisPool.returnResource(jedis);
        }
    }

}
