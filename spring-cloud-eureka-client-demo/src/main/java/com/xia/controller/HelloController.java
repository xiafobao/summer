package com.xia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright @ 2019 SHANZHEN fuli Co. Ltd.
 * All right reserved.
 *
 * @Author xiafobao
 * @Date 2019/4/19
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello eureka!!!!";
    }
}
