package com.xia.demo.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xiafb
 * @date Created in 2020/7/16 17:10
 * description 代理处理类
 * modified By
 * version  1.0
 */
public class JDKProxyHandler implements InvocationHandler {

    /**
     * 被代理对象
     */
    private Object target;

    private IHandler handler;

    public JDKProxyHandler() {
    }

    public JDKProxyHandler(Object target, IHandler handler) {
        this.target = target;
        this.handler = handler;
    }

    public JDKProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(handler != null){
            this.handler.exec(proxy, method, args, target);
        }
        return method.invoke(target, args);
    }

    /**
     * 创建代理对象
     * @param loader 类加载器
     * @return
     */
    public Object createProxyInstance(ClassLoader loader){
        return Proxy.newProxyInstance(loader, target.getClass().getInterfaces(), this);
    }

    public Object createProxyInstance(){
        return createProxyInstance(this.getClass().getClassLoader());
    }
}
