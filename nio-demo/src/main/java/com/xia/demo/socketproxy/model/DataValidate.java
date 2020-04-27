package com.xia.demo.socketproxy.model;

import com.xia.demo.socketproxy.Exception.AuthticationException;

import java.io.IOException;
import java.net.Socket;

public class DataValidate {
    /**
     * 校验鉴权请求
     * @param bytes 请求数据包
     */
    public void validateAuthRequest(byte[] bytes){
        try {
            if(bytes[0]!=0x05){
                throw new AuthticationException();
            }
        } catch (AuthticationException e) {
            e.printStackTrace();
        }
    }
    public void validateAuthResponse(){

    }

    /**
     * 校验传输请求
     * @param bytes 传输数据包
     */
    public void validateTransmitRequest(byte[] bytes){
        if(bytes[0]!=0x05||bytes[2]!=0x00){

        }
    }

    /**
     * 校验传输响应
     * @param host host
     * @param port 端口
     * @return
     */
    public Socket validateTransmitResponse(String host, int port){
        Socket socket=null;
        try {
            socket = new Socket(host,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }
}
