package com.xia.demo.factory.method;

/**
 * @author xiafb
 * @date Created in 2020/5/5 11:19
 * description git图片读取器
 * modified By
 * version  1.0
 */
public class GifImageRead implements ImageRead {
    @Override
    public void readImage() {
        System.out.println("gif图片读取器");
    }
}
