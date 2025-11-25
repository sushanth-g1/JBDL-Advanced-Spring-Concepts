package com.example.gfg_blr_9.services;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserService implements ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    //@Service -> will create a bean of this object
    public UserService(){
        UserService.log.info("UserService getting initiated");
    }
    private PaymentService paymentService;


    @Autowired
    public UserService( @Qualifier("paymentService2") PaymentInterface paymentInterface){
        this.paymentInterface = paymentInterface;
        log.info("UserService ********* initiated");
        paymentInterface.payment();
    }
    private PaymentInterface paymentInterface;

    ApplicationContext applicationContext;
    private RedisDriver redisDriver;

    public void setRedisDriver(RedisDriver redisDriver){
        this.redisDriver = redisDriver;
    }
    public RedisDriver getRedisDriver(){
        //return (RedisDriver) applicationContext.getBean("redisDriverBean");
        return this.redisDriver;
    }

    public void init(){
//        this.redisDriver = (RedisDriver)
        applicationContext.getBean("redisDriverBean");
        log.info(applicationContext.getBean("paymentServiceNew").toString());}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
