package com.xia.demo.netty.linebasedemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.TimeUnit;

/**
 * @author xiafb
 * @date Created in 2019/7/18 11:17
 * description
 * modified By
 * version
 */
public class LineBasedFrameDecoderClient {
    public static void main(String[] args) {
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(worker);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            byte[] bytes1 = ("hello1" + System.getProperty("line.separator")).getBytes();
                            byte[] bytes2 = ("hello2" + System.getProperty("line.separator")).getBytes();
                            byte[] bytes3 = ("hello3" + System.getProperty("line.separator")).getBytes();
                            byte[] bytes4 = System.getProperty("line.separator").getBytes();
                            ByteBuf buffer = Unpooled.buffer();
                            buffer.writeBytes(bytes1);
                            buffer.writeBytes(bytes2);
                            buffer.writeBytes(bytes3);
                            ctx.writeAndFlush(buffer);
                            try{
                                TimeUnit.SECONDS.sleep(2);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            buffer = Unpooled.buffer();
                            buffer.writeBytes(bytes4);
                            ctx.writeAndFlush(buffer);
                        }
                    });
                }
            });
            ChannelFuture sync = bootstrap.connect("127.0.0.1", 8000).sync();
            sync.channel().closeFuture().sync();
        }catch (Exception ex){
            worker.shutdownGracefully();
        }
    }
}
