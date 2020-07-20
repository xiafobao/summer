package com.xia.demo.proxy.jdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xia.demo.proxy.jdk.impl.CarImpl;

import java.lang.reflect.Proxy;

/**
 * @author xiafb
 * @date Created in 2020/7/16 15:43
 * description
 * modified By
 * version
 */
public class JDKProxyTest {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        JDKProxyTest JDKProxyTest = new JDKProxyTest();
        //JDKProxyTest.createProxyInstanceByInterface();
        //JDKProxyTest.createProxyClass();
        JDKProxyTest.createProxyInstanceByInvocationHandler();
    }


    /**
     * 通过类加载器与接口创建代理实例
     * 匿名函数形式
     */
    private void createProxyInstanceByInterface(){
        ICar carProxy = (ICar)Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{ICar.class}, (proxy, method, args) -> {
            System.out.println("当前代理类接口名称：" + proxy.getClass());
            System.out.println("当前执行方法名称：" + method.getName());
            System.out.println("当前执行方法参数：" + objectMapper.writeValueAsString(args));
            return proxy;
        });
        carProxy.carRunning("小卡车");
    }

    /**
     * 通过类加载器与接口创建代理实例
     * 匿名函数形式
     */
    private void createProxyInstanceByInvocationHandler(){
        JDKProxyHandler JDKProxyHandler = new JDKProxyHandler(new CarImpl(), (proxy, method, args, target) -> {
            System.out.println("当前代理类名称：" + target.getClass().getName());
            System.out.println("当前执行方法名称：" + method.getName());
            try {
                System.out.println("当前执行方法参数：" + objectMapper.writeValueAsString(args));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        ICar proxyInstance = (ICar) JDKProxyHandler.createProxyInstance();
        proxyInstance.carRunning("小卡车");
    }

    /**
     * 通过类加载器与接口创建代理实例
     * 匿名函数形式
     */
    private void createProxyClass() {
        Class proxy = Proxy.getProxyClass(this.getClass().getClassLoader(), ICar.class);
        ICar iCar;
        try {
            iCar = (ICar) proxy.newInstance();
            iCar.carRunning("小卡车");
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
