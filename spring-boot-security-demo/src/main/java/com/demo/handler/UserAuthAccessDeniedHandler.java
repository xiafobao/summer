package com.demo.handler;

import com.demo.util.ResultUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiafb
 * @date Created in 2020/5/13 11:34
 * description 拒绝访问处理程序处理类
 * modified By
 * version  1.0
 */
@Component
public class UserAuthAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResultUtil.responseJson(httpServletResponse, ResultUtil.resultCode(403,"未授权"));
    }
}
