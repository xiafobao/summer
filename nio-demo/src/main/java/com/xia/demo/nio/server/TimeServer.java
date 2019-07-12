package com.xia.demo.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author xiafb
 * @date Created in 2019/7/8 11:27
 * description nio
 * modified By
 * version
 */
public class TimeServer {
    private static final ExecutorService executorService = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "TimeServer-线程");
        }
    });

    public static void main(String[] args) throws IOException {
        //打开ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定监听端口
        serverSocketChannel.bind(new InetSocketAddress(9000));
        //设置serverSocketChannel为非阻塞
        serverSocketChannel.configureBlocking(false);
        //打开选择器
        Selector selector = Selector.open();
        //将serverSocketChannel注册到选择器中
        serverSocketChannel.register(selector, serverSocketChannel.validOps());
        //循环查询感兴趣的事件
        while (true){
            //设置超时时间为30s
            int redayCount = selector.select(30 * 1000L);
            //判断选中的感兴趣事件的键是否为空
            if(redayCount == 0){
                continue;
            }
            //获取到选择的感兴趣事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //遍历键集合
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                //判断当前键是否有效
                if(key.isValid()){
                    //判断接受事件是否就绪
                    if(key.isAcceptable()){
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        //获取客户端接收到的连接
                        SocketChannel accept = channel.accept();
                        //并设置为非阻塞
                        accept.configureBlocking(false);
                        //注册到选择器中
                        accept.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
                    }
                    //判断读事件是否就绪
                    if(key.isReadable()){

                    }

                }
                iterator.remove();
            }

        }
    }
}
