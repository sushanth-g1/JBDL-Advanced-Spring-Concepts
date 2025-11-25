package com.example.gfg_blr_9.config;

import com.example.gfg_blr_9.pubsub.RedisMessageListener;
import com.example.gfg_blr_9.pubsub.RedisMessagePublisher;
import com.example.gfg_blr_9.services.RedisDriver;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

@Slf4j
@Configuration
public class DemoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(DemoConfiguration.class);

//    @Bean(name = "redisDriverBean")
//    @Scope(scopeName = "prototype")
//    public RedisDriver getRedisDriver(){
//        log.info("Creating Redis Driver");
//        return new RedisDriver("ii","iioo", 012);
//    }


    @Bean(name="redisConnectionFactory2")
    public LettuceConnectionFactory redisConnectionFactory2() {

        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));
    }

    @Bean
    RedisTemplate<String, String> stringRedisTemplate2() {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory2());
        return template;
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return RedisCacheManager.builder(connectionFactory).cacheDefaults(config).build();

    }

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
        return container;
    }


    @Bean
    MessageListenerAdapter listenerAdapter(RedisMessageListener subscriber) {
        return new MessageListenerAdapter(subscriber, "onMessage");
    }

    @Bean
    RedisMessageListener subscriber() {
        return new RedisMessageListener();
    }

    @Bean
    RedisMessagePublisher publisher(RedisConnectionFactory connectionFactory) {
        return new RedisMessagePublisher(connectionFactory);
    }
}
