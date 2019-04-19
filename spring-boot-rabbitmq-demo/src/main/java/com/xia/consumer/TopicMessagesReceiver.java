package com.xia.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Copyright @ 2019 SHANZHEN fuli Co. Ltd.
 * All right reserved.
 *
 * @Author xiafobao
 * @Date 2019/4/19
 */
@Component
@RabbitListener(queues = "topic.messages")
public class TopicMessagesReceiver {

    @RabbitHandler
    public void process(String msg){
        System.out.println("TopicMessagesReceiver  : " +msg);
    }
}
