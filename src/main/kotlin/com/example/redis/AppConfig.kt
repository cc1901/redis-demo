package com.example.redis

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate


@Configuration
class AppConfig {

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        val jedisConnectionFactory = JedisConnectionFactory()
        jedisConnectionFactory.hostName = "localhost";
        jedisConnectionFactory.port = 6379;
        return jedisConnectionFactory;

    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val template = RedisTemplate<String, Any>()
        template.setConnectionFactory(jedisConnectionFactory())
        return template
    }

}