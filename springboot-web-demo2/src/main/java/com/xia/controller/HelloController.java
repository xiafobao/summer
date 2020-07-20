package com.xia.controller;

import cn.hutool.core.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiafb
 * @date Created in 2019/7/26 17:33
 * description
 * modified By
 * version
 */
@RestController
public class HelloController {

    Logger log = LoggerFactory.getLogger(HelloController.class);

    @Value("${server.port}")
    private String port;

    @GetMapping("/hello")
    public String hello() {
        log.debug("接收请求{}", DateUtil.now());
        return DateUtil.now() + "====hello===端口" +port;
    }
    
}
