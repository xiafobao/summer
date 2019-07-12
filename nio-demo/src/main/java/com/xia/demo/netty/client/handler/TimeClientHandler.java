package com.xia.demo.netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author xiafb
 * @date Created in 2019/7/8 15:34
 * description 客户端处理器
 * modified By
 * version  1.0
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private byte[] req=("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String body = (String) msg;
        System.out.println("Now is :" + body);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ByteBuf msg = Unpooled.buffer(req.length);
        msg.writeBytes(req);
        ctx.writeAndFlush(msg);
    }
}
