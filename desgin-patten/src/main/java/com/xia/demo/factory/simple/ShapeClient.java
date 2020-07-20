package com.xia.demo.factory.simple;

/**
 * @author xiafb
 * @date Created in 2020/5/4 15:56
 * description 客户端调用类型
 * modified By
 * version  1.0
 */
public class ShapeClient {

    public static void main(String[] args) {
        //根据类型获取实例
        Shape shape = ShapeFactory.getShape(ShapeType.SQUARE);
        shape.draw();
        shape.erase();
    }
}
