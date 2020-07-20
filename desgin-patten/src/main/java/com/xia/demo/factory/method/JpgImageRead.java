package com.xia.demo.factory.method;

/**
 * @author xiafb
 * @date Created in 2020/5/5 11:18
 * description jpg图片读取器
 * modified By
 * version  1.0
 */
public class JpgImageRead implements ImageRead {
    @Override
    public void readImage() {
        System.out.println("正在读取jpg图片");
    }
}
