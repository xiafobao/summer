package com.xia.demo.proxy.jdk;

import java.lang.reflect.Method;

/**
 * @author xiafb
 * @date Created in 2020/7/16 17:12
 * description 处理方法
 * modified By
 * version  1.0
 */
@FunctionalInterface
public interface IHandler {

    void exec(Object proxy, Method method, Object[] args, Object target);
}
