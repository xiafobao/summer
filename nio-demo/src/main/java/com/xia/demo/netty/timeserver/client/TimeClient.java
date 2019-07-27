package com.xia.demo.netty.timeserver.client;

import com.xia.demo.netty.timeserver.client.handler.TimeClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author xiafb
 * @date Created in 2019/7/8 15:26
 * description netty time Client
 * modified By
 * version  1.0
 */
public class TimeClient {
    public static void main(String[] args) throws InterruptedException {
        String host = "localhost";
        int port = 8000;
        EventLoopGroup worker = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(worker);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                    socketChannel.pipeline().addLast(new StringDecoder());
                    socketChannel.pipeline().addLast(new TimeClientHandler());
                }
            });
            ChannelFuture sync = bootstrap.connect(host, port).sync();
            sync.channel().closeFuture().sync();
        }finally {
            worker.shutdownGracefully();
        }
    }
}
