package com.xia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
//在启动类上添加@EnableHystrix注解开启Hystrix的熔断器功能。
@EnableHystrix
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudEurekaClientDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaClientDemoApplication.class, args);
    }

}
