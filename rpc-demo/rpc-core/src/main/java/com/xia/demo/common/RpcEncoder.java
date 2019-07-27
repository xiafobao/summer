package com.xia.demo.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author xiafb
 * @date Created in 2019/7/24 15:40
 * description
 * modified By
 * version
 */
public class RpcEncoder extends MessageToByteEncoder {

    private Class<?> genericClass;

    public RpcEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (genericClass.isInstance(msg)) {
            byte[] serialize = SerializationUtil.serialize(msg);
            out.writeInt(serialize.length);
            out.writeBytes(serialize);

        }
    }
}
