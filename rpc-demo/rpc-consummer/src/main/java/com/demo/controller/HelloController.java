package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.demo.api.UserInfoService;
import com.demo.model.ResponseVO;
import com.demo.model.UserVO;
import com.xia.demo.annotation.RpcConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author xiafb
 * @date Created in 2019/7/27 10:43
 * description
 * modified By
 * version
 */
@Slf4j
@RestController
@RequestMapping("/rpc")
public class HelloController {

    @RpcConsumer(providerName = "rpc-provider")
    private UserInfoService userInfoService;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name){
        UserVO userVO = new UserVO();
        userVO.setUserNo("007");
        userVO.setUserName(name);
        userVO.setAge(18);
        ResponseVO<Map<String, UserVO>> userInfo = userInfoService.getUserInfo(userVO);
        log.info("rpc result: {}", JSON.toJSONString(userInfo));
        return JSON.toJSONString(userInfo);
    }

    @GetMapping("/hello2/{name}")
    public String hello2(@PathVariable("name") String name){
        ResponseVO<String> userInfo = userInfoService.sayHello(name);
        log.info("rpc result: {}", JSON.toJSONString(userInfo));
        return JSON.toJSONString(userInfo);
    }

    @GetMapping("/hello3")
    public String hello3(){
        return userInfoService.hello();
    }
}
