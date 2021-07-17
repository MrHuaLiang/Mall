package com.hualiang.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.Jedis;

@PropertySource(value = {"classpath:middleware.properties",}, encoding = "utf-8")
@Configuration
public class RedisConfig {
    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private String port;

    @Value("${redis.pwd}")
    private String pwd;

    @Bean("jedis")
    public Jedis jedis(){
        Jedis jedis = new Jedis(host, Integer.parseInt(port));
        jedis.auth(pwd);
        return jedis;
    }
}
