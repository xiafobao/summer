package com.xia.demo.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author xiafb
 * @date Created in 2019/7/24 15:33
 * description
 * modified By
 * version
 */
public class RpcDecoder extends ByteToMessageDecoder {

    private Class<?> genericClass;

    public RpcDecoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }

        in.markReaderIndex();
        int dataLength = in.readInt();
        if (dataLength < 0) {
            ctx.close();
        }

        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
        }
        //将ByteBuf转换为byte[]
        byte[] bytes = new byte[dataLength];
        in.readBytes(bytes);
        //将data转成object
        Object deserialize = SerializationUtil.deserialize(bytes, genericClass);
        out.add(deserialize);
    }
}
