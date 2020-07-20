package com.xia.demo.factory.method;

/**
 * @author xiafb
 * @date Created in 2020/5/5 11:20
 * description jpg文件读取器工厂
 * modified By
 * version
 */
public class JpgImageReadFactory implements ImageReadFactory {
    @Override
    public ImageRead createImageRead() {
        return new JpgImageRead();
    }
}
