package com.xia.demo.proxy.jdk.impl;

import com.xia.demo.proxy.jdk.ICar;

/**
 * @author xiafb
 * @date Created in 2020/7/16 15:42
 * description
 * modified By
 * version
 */
public class CarImpl implements ICar {

    @Override
    public void carRunning(String arg) {
        System.out.println(arg + " is running ");
    }
}
