package com.xia.demo.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xiafb
 * @date Created in 2019/12/16 16:28
 * description
 * modified By
 * version
 */
public class BeanAnnotationTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigBean.class);
        Person bean = applicationContext.getBean(Person.class);
        System.out.println(bean);
        String[] beanNamesForType = applicationContext.getBeanDefinitionNames();
        for (String name : beanNamesForType) {
            System.out.println(name);
        }
        ((AnnotationConfigApplicationContext) applicationContext).close();
    }
}

