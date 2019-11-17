package com.luojia.redis;

import redis.clients.jedis.Jedis;

public class TestMaster {
    public static void main(String[] args) {
        Jedis jedis_M = new Jedis("192.168.0.110", 6379);
        Jedis jedis_S = new Jedis("192.168.0.110", 6380);
        
        // 将 jedis_S 设置为从机，跟随端口为6379的主机
        jedis_S.slaveof("192.168.0.110", 6379);
        
        jedis_M.set("class", "1122");
        String result = jedis_S.get("class");
        
        System.out.println(result);
        
    }

}
