package com.example.gfg_blr_9.pubsub;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class RedisMessagePublisher {
    private final StringRedisTemplate redisTemplate;
    private final ChannelTopic topic;

    public RedisMessagePublisher(RedisConnectionFactory connectionFactory) {
        this.redisTemplate = new StringRedisTemplate(connectionFactory);
        this.topic = new ChannelTopic("chat");
    }

    public void publish(String message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
