package com.xia.demo.nio.client;

import com.xia.demo.nio.utils.CodecUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * @author xiafb
 * @date Created in 2019/7/5 9:22
 * description NIO客户端
 * modified By
 * version  1.0
 */
public class NioClient {

    private SocketChannel clientSocketChannel;
    private Selector selector;
    private List<String> responseQueue = new ArrayList<String>();
    private CountDownLatch connected = new CountDownLatch(1);

    public NioClient() throws IOException, InterruptedException {
        //打开client socket channel
        clientSocketChannel = SocketChannel.open();
        //配置为非阻塞
        clientSocketChannel.configureBlocking(false);
        //创建selector
        selector = Selector.open();
        //注册client socket channel到selector
        clientSocketChannel.register(selector, SelectionKey.OP_CONNECT);
        //连接服务器
        clientSocketChannel.connect(new InetSocketAddress(8000));
        CompletableFuture.runAsync(() -> {
            try {
                handleKeys();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        if (connected.getCount() != 0) {
            connected.await();
        }
        System.out.println("client 启动完成");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        NioClient client = new NioClient();
        for (int i = 0; i < 30; i++) {
            System.out.println("请输入需要发送的内容：");
            Scanner sc = new Scanner(System.in);
            client.send(sc.nextLine());
            Thread.sleep(1000L);
        }
    }

    private void handleKeys() throws IOException {
        while (true) {
            //通过selector选择channel
            int select = selector.select(30 * 1000L);
            if (select == 0) {
                continue;
            }
            //遍历可选择的channel的selectionKey集合
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (!key.isValid()) {
                    continue;
                }
                handleKey(key);
            }
        }
    }

    private void handleKey(SelectionKey key) throws IOException {
        // 接受连接就绪
        if (key.isConnectable()) {
            handleConnectableKey(key);
        }
        // 读就绪
        if (key.isReadable()) {
            handleReadableKey(key);
        }
        // 写就绪
        if (key.isWritable()) {
            handleWritableKey(key);
        }
    }

    private void handleConnectableKey(SelectionKey key) throws IOException {
        // 完成连接
        if (!clientSocketChannel.isConnectionPending()) {
            return;
        }
        clientSocketChannel.finishConnect();
        // log
        System.out.println("接受新的 Channel");
        // 注册 Client Socket Channel 到 Selector
        clientSocketChannel.register(selector, SelectionKey.OP_READ, responseQueue);
        // 标记为已连接
        connected.countDown();
    }

    @SuppressWarnings("Duplicates")
    private void handleReadableKey(SelectionKey key) throws ClosedChannelException {
        // Client Socket Channel
        SocketChannel clientSocketChannel = (SocketChannel) key.channel();
        // 读取数据
        ByteBuffer readBuffer = CodecUtil.read(clientSocketChannel);
        // 打印数据
        if (readBuffer.position() > 0) {
            // 写入模式下，
            String content = CodecUtil.newString(readBuffer);
            System.out.println("读取数据：" + content);
        }
    }

    @SuppressWarnings("Duplicates")
    private void handleWritableKey(SelectionKey key) throws ClosedChannelException {
        // Client Socket Channel
        SocketChannel clientSocketChannel = (SocketChannel) key.channel();

        // 遍历响应队列
        List<String> responseQueue = (ArrayList<String>) key.attachment();
        for (String content : responseQueue) {
            // 返回
            CodecUtil.write(clientSocketChannel, content);
        }
        responseQueue.clear();

        // 注册 Client Socket Channel 到 Selector
        clientSocketChannel.register(selector, SelectionKey.OP_READ, responseQueue);
    }

    private synchronized void send(String content) throws ClosedChannelException {
        // 添加到响应队列
        responseQueue.add(content);
        // 打印数据
        System.out.println("写入数据：" + content);
        // 注册 Client Socket Channel 到 Selector
        clientSocketChannel.register(selector, SelectionKey.OP_WRITE, responseQueue);
        selector.wakeup();
    }
}
