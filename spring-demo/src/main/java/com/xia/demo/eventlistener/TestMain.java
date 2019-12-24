package com.xia.demo.eventlistener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiafb
 * @date Created in 2019/12/24 15:58
 * description
 * modified By
 * version
 */
@Configuration
@ComponentScan("com.xia.demo.eventlistener")
public class TestMain {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestMain.class);
        DemoPublisher bean = context.getBean(DemoPublisher.class);
        bean.publishMsg();
        ((AnnotationConfigApplicationContext) context).close();
    }

}
