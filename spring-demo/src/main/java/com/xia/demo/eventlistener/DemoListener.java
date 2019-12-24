package com.xia.demo.eventlistener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author xiafb
 * @date Created in 2019/12/24 15:46
 * description spring事件监听类
 * modified By
 * version
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {
    @Override
    public void onApplicationEvent(DemoEvent demoEvent) {
        String msg = demoEvent.getMsg();
        System.out.println("DemoListener接受到了demoEvent发布的消息："+msg);
    }
}
