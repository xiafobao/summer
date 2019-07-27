package com.xia.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
            String str = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +  "第" + i + "条" + msg;
            amqpTemplate.convertAndSend("summer", str);
        }
        return "消息：" + msg + ". ===> 已发送100条!";
    }

    @GetMapping("/topicSend")
    public String topicSend(String msg){
        int len = 10;
        for (int i = 0; i < len; i++){
            String str = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS")) + "===>第"+i+"条topic.message消息：" + msg;
            amqpTemplate.convertAndSend("summer-exchange", "topic.zz", str);
        }
        //for (int i = 0; i < len; i++) {
        //    String str = "第"+i+"条topic.messages消息：" + msg + "===> 发送时间为：" + System.currentTimeMillis();
        //    amqpTemplate.convertAndSend("summer-exchange", "topic.me", str);
        //}
        return "消息：" + msg + ". ===> 已发送"+len+"条!";
    }
}
