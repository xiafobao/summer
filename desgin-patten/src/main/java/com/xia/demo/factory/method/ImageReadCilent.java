package com.xia.demo.factory.method;

/**
 * @author xiafb
 * @date Created in 2020/5/5 11:23
 * description 文件读取器客户端
 * modified By
 * version  1.0
 */
public class ImageReadCilent {
    public static void main(String[] args) {
        ImageReadFactory gifImageReadFactory = new GifImageReadFactory();
        ImageRead imageRead = gifImageReadFactory.createImageRead();
        imageRead.readImage();
    }
}
