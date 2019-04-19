package com.xia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudEurekaClientDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaClientDemoApplication.class, args);
    }

}
