package com.xia.demo.factory.simple;

/**
 * @author xiafb
 * @date Created in 2020/5/4 15:50
 * description 图形工厂类
 * modified By
 * version  1.0
 */
public class ShapeFactory {

    /**
     * 根据图形类型获取图形
     * @param shapeType 图形类型
     * @return
     */
    public static Shape getShape(ShapeType shapeType){
        switch (shapeType){
            case SQUARE:
                return new Square();
            case Circular:
                return new Circular();
            case TRIANGLE:
                return new Triangle();
            default:
                throw new UnSupportedShapeException("未找到相应图形");
        }
    }
}
