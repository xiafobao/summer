package com.xia.demo.proxy.cglib;

import com.xia.demo.proxy.jdk.impl.CarImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @author xiafb
 * @date Created in 2020/7/16 18:15
 * description
 * modified By
 * version
 */
public class CglbProxyTest {

    public static void main(String[] args) {
        MethodInterceptor interceptor = (obj, method, arg, proxy) -> {
            System.out.println("通过cglib代理");
            return proxy.invokeSuper(obj, arg);
        };

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CarImpl.class);
        enhancer.setCallback(interceptor);

        CarImpl o = (CarImpl) enhancer.create();
        o.carRunning("TOM");

    }
}
