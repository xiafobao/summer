package com.demo.api;

import com.demo.model.ResponseVO;
import com.demo.model.UserVO;

import java.util.Map;

/**
 * @author xiafb
 * @date Created in 2019/7/26 17:43
 * description
 * modified By
 * version
 */
public interface UserInfoService {

    ResponseVO<Map<String, UserVO>> getUserInfo(UserVO userVO);

    String hello();


    ResponseVO<String> sayHello(String name);
}
