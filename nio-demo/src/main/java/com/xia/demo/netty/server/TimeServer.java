package com.xia.demo.netty.server;

import com.xia.demo.netty.server.handler.TimeServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author xiafb
 * @date Created in 2019/7/8 15:09
 * description
 * modified By
 * version
 */
public class TimeServer {
    private void run() throws Exception {
        //创建客户端连接事件循环组（线程组）
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        //创建客户端请求处理事件循环组（线程组）
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new TimeServerHandler());
                        }
                    });
            int port = 8000;
            ChannelFuture sync = serverBootstrap.bind(port).sync();
            System.out.println("TimeServer Started on 8000... ");
            sync.channel().closeFuture().sync();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new TimeServer().run();
    }
}
