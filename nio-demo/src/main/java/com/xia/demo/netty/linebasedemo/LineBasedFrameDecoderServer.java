package com.xia.demo.netty.linebasedemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author xiafb
 * @date Created in 2019/7/18 11:06
 * description 通过行分割符解码
 * modified By
 * version
 */
public class LineBasedFrameDecoderServer {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, worker);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new LineBasedFrameDecoder(1024, true, true));
                    //自定义打印拆包结果
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            if(msg instanceof ByteBuf){
                                ByteBuf by = (ByteBuf) msg;
                                System.out.println(LocalDateTime.now().format(DATE_TIME_FORMATTER) + "===>" + by.toString(Charset.defaultCharset()));
                            }
                        }
                    });
                }
            });
            ChannelFuture future = serverBootstrap.bind(8000).sync();
            System.out.println("LineBasedFrameDecoderServer start in 8000.....");
            future.channel().closeFuture().sync();
        }catch (Exception ex){
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }
}
