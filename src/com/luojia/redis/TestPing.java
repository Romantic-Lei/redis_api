package com.luojia.redis;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class TestPing {
	public static void main(String[] args) {
		// 链接本地或者虚拟机的redis服务
		Jedis jedis = new Jedis("192.168.0.110", 6379);
		//查看服务是否运行，打出pong表示OK
		System.out.println(jedis.ping());
		
		jedis.set("k1", "v1");
		jedis.set("k2", "v2");
		jedis.set("k3", "v3");
		
		System.out.println(jedis.get("k1"));
		System.out.println(jedis.get("k2"));
		System.out.println(jedis.get("k3"));
		
		Set<String> sets = jedis.keys("*");
		System.out.println(sets.size());
	}

}
