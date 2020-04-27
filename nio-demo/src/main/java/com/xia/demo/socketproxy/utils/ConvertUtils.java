package com.xia.demo.socketproxy.utils;

public class ConvertUtils {
    //byte数组转换为十六进制的字符串
    public String bytesToHexString(byte[] bytes, int begin, int end) {
        StringBuilder sb = new StringBuilder(bytes.length);
        String sTemp;
        for (int i = begin; i < end; i++) {
            sTemp = Integer.toHexString(0xFF & bytes[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
            sb.append(" ");
        }
        return sb.toString();
    }
    //pojo转换为byte数组
    public <T> byte[] pojoTobytes(T x) {
        return null;
    }
}
