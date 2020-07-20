package com.xia.demo.factory.simple;

/**
 * @author xiafb
 * @date Created in 2020/5/4 15:46
 * description 三角形
 * modified By
 * version  1.0
 */
public class Triangle extends Shape {
    @Override
    void draw() {
        System.out.println("绘制三角形");
    }

    @Override
    void erase() {
        System.out.println("擦除三角形");
    }
}
