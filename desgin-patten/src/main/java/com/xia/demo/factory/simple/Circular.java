package com.xia.demo.factory.simple;

/**
 * @author xiafb
 * @date Created in 2020/5/4 15:48
 * description 圆形
 * modified By
 * version  1.0
 */
public class Circular extends Shape {
    @Override
    void draw() {
        System.out.println("绘制圆形");
    }

    @Override
    void erase() {
        System.out.println("擦除圆形");
    }
}
