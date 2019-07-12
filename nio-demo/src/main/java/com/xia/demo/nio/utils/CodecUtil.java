package com.xia.demo.nio.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author xiafb
 * @date Created in 2019/7/4 17:33
 * description
 * modified By
 * version
 */
public class CodecUtil {

    public static ByteBuffer read(SocketChannel channel) {
        // 注意，不考虑拆包的处理
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            int count = channel.read(buffer);
            if (count == -1) {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return buffer;
    }

    public static String newString(ByteBuffer buffer) {
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        System.arraycopy(buffer.array(), buffer.position(), bytes, 0, buffer.remaining());
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static void write(SocketChannel channel, String content) {
        // 写入 Buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(content.getBytes(StandardCharsets.UTF_8));
        // 写入 Channel
        buffer.flip();
        try {
            // 注意，不考虑写入超过 Channel 缓存区上限。
            channel.write(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
