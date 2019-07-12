package com.xia.demo.nio.server;

import com.xia.demo.nio.utils.CodecUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xiafb
 *
 *
 *
 *
 * @date Created in 2019/7/4 15:42
 * description nio服务端
 * modified By
 * version  1.0
 */
public class NioServer {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public NioServer() throws IOException {
        //打开server socket channel
        serverSocketChannel = ServerSocketChannel.open();
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //绑定服务端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8000));
        //创建selector
        selector = Selector.open();
        //注册server socket channel到selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server 启动完成");
        handleKeys();
    }

    private void handleKeys() throws IOException{
        while (true) {
            //通过selector选择channel
            int selectNums = selector.select(30 * 1000L);
            if (selectNums == 0) {
                continue;
            }
            System.out.println("选择channel的数量：" + selectNums);
            //遍历可选择的channel的selectKey集合
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //移除需要被处理的SelectionKey
                iterator.remove();
                //忽略无效的SelectionKey
                if (!key.isValid()) {
                    continue;
                }
                handleKey(key);
            }

        }
    }

    private void handleKey(SelectionKey key) {
        //接受连接请求
        if(key.isAcceptable()){
            handleAcceptablekey(key);
        }
        //读就绪
        if(key.isReadable()){
            handleReadablekey(key);
        }
        //写就绪
        if(key.isWritable()){
            handleWriteablekey(key);
        }
    }

    /**
     * 处理连接请求
     * @param key 连接key
     */
    private void handleAcceptablekey(SelectionKey key){
        try {
            //接受 Client socket channel
            SocketChannel clientSocketChannel = ((ServerSocketChannel) key.channel()).accept();
            //配置为非阻塞
            clientSocketChannel.configureBlocking(false);
            //log
            System.out.println("接受一个新连接："+ key.toString());
            //注册Client  socket channel到selector
            clientSocketChannel.register(selector, SelectionKey.OP_READ, new ArrayList<String>());
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }

    /**
     * 处理读请求
     * @param key 读就绪key
     */
    private void handleReadablekey(SelectionKey key) {
        try {
            //client socket channel
            SocketChannel clientSocketChannel  = (SocketChannel) key.channel();
            if(!clientSocketChannel.isOpen()){
                System.out.println("连接已关闭");
                clientSocketChannel.close();
            }
            //读取数据
            ByteBuffer read = CodecUtil.read(clientSocketChannel);
            if(read == null){
                System.out.println("断开channel");
                clientSocketChannel.register(selector, 0);
                return;
            }
            //打印数据
            if(read.position() > 0){
                String str = CodecUtil.newString(read);
                System.out.println("服务端读取数据：" + str);
                List<String> attachment = (List<String>) key.attachment();
                attachment.add("收到（OJBK）：" + str);
                //注册client socket channel到selector中
                clientSocketChannel.register(selector, SelectionKey.OP_WRITE, key.attachment());
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * 处理写请求
     * @param key 写就绪key
     */
    private void handleWriteablekey(SelectionKey key){
        //client socket channel
        SocketChannel clientSocketChannel  = (SocketChannel) key.channel();
        //遍历响应队列
        List<String> attachment = (List<String>) key.attachment();
        for (String content : attachment){
            //打印数据
            System.out.println("写入数据：" + content);
            CodecUtil.write(clientSocketChannel, content);
        }
        attachment.clear();
        try {
            //注册client socket channel到selector中
            clientSocketChannel.register(selector, SelectionKey.OP_READ, attachment);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        NioServer nioServer = new NioServer();
    }
}
