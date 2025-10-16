package com.example.gfg_blr_9.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service

public class PaymentService implements PaymentInterface {
    @Override
    public void payment() {
        PaymentInterface.super.payment();
    }
}
