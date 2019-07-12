package com.xia.demo.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @author xiafb
 * @date Created in 2019/7/8 15:18
 * description 事件服务器处理器
 * modified By
 * version  1.0
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String request = (String) msg;
        String response = null;
        if("QUERY TIME ORDER".equals(request)){
            response = new Date(System.currentTimeMillis()).toString();
        }else {
            response = "HTTP/1.1 200 OK \n" +
                       "Content-Type: text/html\n" +
                       "\n" +
                       "BAD_REQUEST";
        }
        response = response + System.getProperty("line.separator");
        ByteBuf byteBuf = Unpooled.copiedBuffer(response.getBytes());
        ctx.writeAndFlush(byteBuf);
    }
}
