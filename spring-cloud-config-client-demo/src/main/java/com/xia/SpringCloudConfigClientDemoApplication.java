package com.xia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringCloudConfigClientDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigClientDemoApplication.class, args);
    }

}
