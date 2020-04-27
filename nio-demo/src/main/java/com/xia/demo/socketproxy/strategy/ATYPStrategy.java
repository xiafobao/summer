package com.xia.demo.socketproxy.strategy;

public interface ATYPStrategy {

    /**
     * 从数据包中获取host
     * @param bytes 数据包
     * @return host字符串
     */
    public String getHost(byte[] bytes);

    /**
     * 从数据包中获取端口
     * @param bytes 数据包
     * @return 端口
     */
    public int getPort(byte[] bytes);

    /**
     * 获取响应信息
     * @param bytes 请求数据包
     * @param connect 响应连接数据包
     * @return 响应数据
     */
    public byte[] getResponse(byte[] bytes, byte[] connect);
}
