package com.demo.handler;

import com.demo.util.ResultUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiafb
 * @date Created in 2020/5/13 11:36
 * description 认证入口点
 * modified By
 * version  1.0
 */
@Component
public class UserAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResultUtil.responseJson(httpServletResponse,ResultUtil.resultCode(401,"未登录"));
    }
}
