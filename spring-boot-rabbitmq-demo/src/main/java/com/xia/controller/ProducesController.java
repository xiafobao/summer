package com.xia.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright @ 2019 SHANZHEN fuli Co. Ltd.
 * All right reserved.
 *
 * @Author xiafobao
 * @Date 2019/4/18
 */
@RestController
@RequestMapping("/rabbit")
public class ProducesController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/send")
    public String send(String msg){
        for (int i = 0; i < 100; i++){
            String str = i + msg + "--->" + System.currentTimeMillis();
            amqpTemplate.convertAndSend("summer", str);
        }
        return "消息：" + msg + ". ===> 已发送100条!";
    }

    @GetMapping("/topicSend")
    public String topicSend(String msg){
        for (int i = 0; i < 100; i++){
            String str = "第"+i+"条topic.message消息：" + msg + "===> 发送时间为：" + System.currentTimeMillis();
            amqpTemplate.convertAndSend("exchange", "topic.message", str);
        }
        for (int i = 0; i < 100; i++) {
            String str = "第"+i+"条topic.messages消息：" + msg + "===> 发送时间为：" + System.currentTimeMillis();
            amqpTemplate.convertAndSend("exchange", "topic.messages", str);
        }
        return "消息：" + msg + ". ===> 已发送100条!";
    }
}
