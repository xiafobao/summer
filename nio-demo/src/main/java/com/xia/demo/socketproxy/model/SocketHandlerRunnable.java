package com.xia.demo.socketproxy.model;

import com.xia.demo.socketproxy.strategy.ATYPStrategy;
import com.xia.demo.socketproxy.strategy.atypStrategy.DomainStrategy;
import com.xia.demo.socketproxy.strategy.atypStrategy.IpvfStrategy;
import com.xia.demo.socketproxy.transport.TransferDataRunnable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketHandlerRunnable implements Runnable{
    private InputStream inputStreamFromClient;
    private OutputStream outputStreamToClient;
    private Socket socketClient;
    private InputStream inputStreamFromServer;
    private OutputStream outputStreamToServer;
    private Socket socketServer;
    private DataValidate validate = new DataValidate();
    private byte[] bytes = new byte[270];
    /**
     * 响应握手信息数据
     */
    private static final byte[] AUTHRESPONSE = { 0x05, 0x00 };
    /**
     * 响应允许连接数据
     */
    private static final byte[] CONNECT = { 0x05, 0x00,0x00,0x01,0,0,0,0 ,0,0};

    public SocketHandlerRunnable(Socket socket){
        this.socketClient =socket;
    }

    /**
     * 认证权限（握手连接）
     */
    private void confirmAuth(){
        System.out.println("\n\n接收一个新连接===>" + socketClient.getInetAddress() + ":" + socketClient.getPort());
        try {
            inputStreamFromClient = socketClient.getInputStream();
            outputStreamToClient = socketClient.getOutputStream();
            int len = inputStreamFromClient.read(bytes);
            //校验请求数据包第一个字节是否为sockos5协议版本
            validate.validateAuthRequest(bytes);
            //代理收到请求包确认是sockos5协议请求后响应握手信息
            outputStreamToClient.write(AUTHRESPONSE);
            outputStreamToClient.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 执行转发请求
     * @throws IOException
     */
    private void transmit() throws IOException{
        int len = inputStreamFromClient.read(bytes);
        //校验协议请求信息
        validate.validateTransmitRequest(bytes);
        //判断请求地址类型（1-ipv4 3-域名 4-ipv6）
        ATYPStrategy atypStrategy;
        if(bytes[3]==0x03){
            //提取域名信息
            atypStrategy = new DomainStrategy(bytes[4]);
        }else{
            //提取IP信息
            atypStrategy = new IpvfStrategy();
        }
        socketServer = validate.validateTransmitResponse(atypStrategy.getHost(bytes), atypStrategy.getPort(bytes));
        inputStreamFromServer = socketServer.getInputStream();
        outputStreamToServer = socketServer.getOutputStream();
        outputStreamToClient.write(atypStrategy.getResponse(bytes,CONNECT));
        outputStreamToClient.flush();
    }
    @Override
    public void run() {
        try {
            confirmAuth();
            transmit();
            Thread sourceThread=new Thread(new TransferDataRunnable(inputStreamFromClient, outputStreamToServer));
            sourceThread.start();
            Thread responseThread=new Thread(new TransferDataRunnable(inputStreamFromServer, outputStreamToClient));
            responseThread.start();
            sourceThread.join();
            responseThread.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socketClient !=null) {
                    socketClient.close();
                    socketClient =null;
                }
                if (socketServer != null) {
                    socketServer.close();
                    socketServer =null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
