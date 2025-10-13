package com.example.gfg_blr_9.services;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    //@Service -> will create a bean of this object
    public UserService(){
        UserService.log.info("UserService getting initiated");
    }
    private PaymentService paymentService;



}
