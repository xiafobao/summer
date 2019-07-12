package com.xia.demo.nio.server;


import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Calendar;

/**
 * @author xiafb
 * @date Created in 2019/7/8 11:49
 * description 任务处理类
 * modified By
 * version  1.0
 */
public class TimeServerTask implements Runnable {
    private SelectionKey selectionKey;

    public TimeServerTask(SelectionKey selectionKey){
        this.selectionKey = selectionKey;
    }

    @Override
    public void run() {
        SocketChannel channel = (SocketChannel ) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            int count = 0;
            while ((count = channel.read(byteBuffer)) > 0) {
                byteBuffer.flip();
                byte[] request = new byte[byteBuffer.remaining()];
                byteBuffer.get(request);
                String requestStr = new String(request);
                byteBuffer.clear();
                if(!"GET CURRENT TIME".equals(requestStr)){
                    channel.write(byteBuffer.put("BAD_REQUEST".getBytes()));
                }else {
                    byteBuffer.put(Calendar.getInstance().getTime().toLocaleString().getBytes());
                    byteBuffer.flip();
                    channel.write(byteBuffer);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
            selectionKey.cancel();
        }
    }
}

