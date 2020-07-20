package com.xia.demo.factory.simple;

/**
 * @author xiafb
 * @date Created in 2020/5/4 15:47
 * description 正方形
 * modified By
 * version  1.0
 */
public class Square extends Shape {
    @Override
    void draw() {
        System.out.println("绘制正方形");
    }

    @Override
    void erase() {
        System.out.println("擦除正方形");
    }
}
