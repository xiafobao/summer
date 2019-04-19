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
@RabbitListener(queues = "topic.message")
public class TopicMessageReceiver {

    @RabbitHandler
    public void process(String msg){
        System.out.println("TopicMessageReceiver  : " +msg);
    }
}
