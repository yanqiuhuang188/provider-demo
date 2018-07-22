package com.nimble.provider.controller;


import com.netflix.discovery.EurekaClient;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class OfflineController {

    @Lazy
    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping("/offline")
    public synchronized void offline(){
        eurekaClient.shutdown();
    }

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @GetMapping("/test")
    public void test() throws SQLException {
        Connection connection = dataSource.getConnection();
        String schema = connection.getSchema();
        System.out.println(schema);
    }
}
