package com.xia.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Copyright @ 2019 SHANZHEN fuli Co. Ltd.
 * All right reserved.
 *
 * @Author xiafobao
 * @Date 2019/4/18
 */
@Component
@RabbitListener(queues = "summer")
@Slf4j
public class ConsumerB {
    @RabbitHandler
    public void process(String message){
        log.info("消费者B===rabbitmq接收的消息为：{}", message);
    }
}
