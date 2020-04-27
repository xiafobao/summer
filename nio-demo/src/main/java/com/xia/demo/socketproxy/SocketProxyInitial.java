package com.xia.demo.socketproxy;

import com.xia.demo.socketproxy.model.SocketHandlerRunnable;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 代理服务端
 */
public class SocketProxyInitial {
    public static void main(String[] args) {
        try {
            //创建代理服务端
            ServerSocket serverSocket = new ServerSocket(1080);
            DefaultThreadFactory defaultThreadFactory = new DefaultThreadFactory("代理连接");
            //创建线程池用于执行代理请求任务
            ThreadPoolExecutor boss = new ThreadPoolExecutor(50, 100,
                    0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), defaultThreadFactory);
            while (true) {
                Socket socket = serverSocket.accept();
                boss.submit(new SocketHandlerRunnable(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
