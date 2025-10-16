package com.example.gfg_blr_9.config;

import com.example.gfg_blr_9.services.RedisDriver;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Slf4j
@Configuration
public class DemoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(DemoConfiguration.class);

    @Bean(name = "redisDriverBean")
    @Scope(scopeName = "prototype")
    public RedisDriver getRedisDriver(){
        log.info("Creating Redis Driver");
        return new RedisDriver("ii","iioo", 012);
    }
}
