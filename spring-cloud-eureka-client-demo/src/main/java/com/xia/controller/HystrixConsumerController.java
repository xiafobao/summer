package com.xia.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 消费者
 */
@Slf4j
@RestController
@RequestMapping("/hystrix/consumer")
public class HystrixConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 调用 user微服务
     */
    @HystrixCommand(fallbackMethod = "getDefaultUser")
    @GetMapping("/getUser")
    public String getUser() {
        String url = "http://spring-cloud-config-client-demo/config/client/getRepositoryUrl";
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return restTemplate.getForObject(url, String.class);
    }

    public String getDefaultUser() {
        System.out.println("熔断，默认回调函数");
        return "{\"id\":-1,\"name\":\"熔断用户\",\"password\":\"123456\"}";
    }
}