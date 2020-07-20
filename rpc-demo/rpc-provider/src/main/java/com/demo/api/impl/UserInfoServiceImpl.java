package com.demo.api.impl;

import com.alibaba.fastjson.JSON;
import com.demo.api.UserInfoService;
import com.demo.model.ResponseVO;
import com.demo.model.UserVO;
import com.xia.demo.annotation.RpcService;
import lombok.extern.slf4j.Slf4j;


/**
 * @author xiafb
 * @date Created in 2019/7/27 10:29
 * description
 * modified By
 * version
 */
@Slf4j
@RpcService(UserInfoService.class)
public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public ResponseVO<UserVO> getUserInfo(UserVO userVO) {
        log.debug("接收到数据: {}", JSON.toJSONString(userVO));
        return ResponseVO.success(userVO);
    }

    @Override
    public String hello() {
        return "simple hello word!";
    }

    @Override
    public ResponseVO<String> sayHello(String name) {
        return ResponseVO.success("has params and return hello!");
    }
}
