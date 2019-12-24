package com.xia.demo.eventlistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author xiafb
 * @date Created in 2019/12/24 15:49
 * description 事件发布类
 * modified By
 * version  1.0
 */
@Component
public class DemoPublisher {

    @Autowired
    private ApplicationContext applicationContext;


    public void publishMsg(){
        DemoEvent demoEvent = new DemoEvent(this);
        demoEvent.setMsg("hello world!");
        System.out.println("发布消息:" + demoEvent);
        applicationContext.publishEvent(demoEvent);
    }
}
