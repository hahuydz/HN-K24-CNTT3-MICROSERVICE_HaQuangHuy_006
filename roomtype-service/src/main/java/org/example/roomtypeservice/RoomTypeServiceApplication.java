package org.example.roomtypeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RoomTypeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomTypeServiceApplication.class, args);
    }
}
