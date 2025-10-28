package com.example.gfg_blr_9.entity_listeners;

import jakarta.persistence.PostPersist;

public class UserEntityListeners {
    @PostPersist
    public void afterPersist(){
        System.out.println("library user persisted");
    }
}
