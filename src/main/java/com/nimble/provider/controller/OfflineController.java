package com.nimble.provider.controller;


import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfflineController {

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping("/offline")
    public synchronized void offline(){
        eurekaClient.shutdown();
    }
}
