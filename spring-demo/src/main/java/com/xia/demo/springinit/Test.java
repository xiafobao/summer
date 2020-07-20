package com.xia.demo.springinit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xiafb
 * @date Created in 2020/7/11 10:30
 * description
 * modified By
 * version
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        //InitTestBean bean = applicationContext.getBean(InitTestBean.class);
    }
}
