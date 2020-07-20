package com.demo.handler;

import com.demo.config.JWTConfig;
import com.demo.entity.SelfUserEntity;
import com.demo.util.JWTTokenUtil;
import com.demo.util.ResultUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiafb
 * @date Created in 2020/5/19 10:18
 * description 登陆成功处理类
 * modified By
 * version  1.0
 */
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        SelfUserEntity principal = (SelfUserEntity) authentication.getPrincipal();
        String accessToken = JWTTokenUtil.createAccessToken(principal);
        accessToken = JWTConfig.tokenPrefix + accessToken;
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "登录成功");
        map.put("token", accessToken);
        ResultUtil.resultSuccess(map);
    }
}
