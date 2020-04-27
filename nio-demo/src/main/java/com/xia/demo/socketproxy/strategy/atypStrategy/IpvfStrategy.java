package com.xia.demo.socketproxy.strategy.atypStrategy;


import com.xia.demo.socketproxy.strategy.ATYPStrategy;

import java.util.Arrays;

public class IpvfStrategy implements ATYPStrategy {
    private StringBuffer sb;

    public IpvfStrategy() {
        sb=new StringBuffer();
    }
    @Override
    public String getHost(byte[] bytes) {
        for (int i = 4; i < 8; i++) {
            sb.append((0xFF & bytes[i]));
            sb.append(".");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println("host===>"+sb.toString());
        return sb.toString();
    }

    @Override
    public int getPort(byte[] bytes) {
        int port=0;
        for (int i = 8; i < 10; i++) {
            port <<= 8;
            port += (0xFF&bytes[i]);
        }
        System.out.println("port===>"+port);
        return port;
    }
    @Override
    public byte[] getResponse(byte[] bytes,byte[] connect){
        System.arraycopy(bytes, 4, connect, 4, 6);
        System.out.println("response===>"+ Arrays.toString(connect));
        return connect;
    }
}
