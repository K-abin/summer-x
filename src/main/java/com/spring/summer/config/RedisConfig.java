package com.spring.summer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;

import javax.annotation.Resource;

/**
 * @Author CXB
 * @ClassName RedisConfig
 * @date 2022/8/18 13:39
 * @Description Redis序列化优化
 */
@Configuration
public class RedisConfig {

    @Resource
    public RedisConnectionFactory redisConnectionFactory;

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory){
//        创建redisTemplate 对象
        RedisTemplate<Object,Object> template = new RedisTemplate<>();
//        连接工厂
        template.setConnectionFactory(factory);
//        创建序列化的工具
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        设置key 的序列化
        template.setKeySerializer(serializer);
        template.setHashValueSerializer(serializer);
//        设置value的序列化
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        return template;
    }

//    接入脚本
//    @Bean
//    DefaultRedisScript limitScript(){
//        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
//        script.setResultType(long.class);
//        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/limit.lua")));
//        return script;
//    }
}
