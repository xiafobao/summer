package com.xia.demo.factorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xiafb
 * @date Created in 2019/12/17 11:18
 * description
 * modified By
 * version
 */
public class FactoryBeanTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(FactoryBeanConfig.class);
        Object getMemberFactoryBean = applicationContext.getBean("getMemberFactoryBean");
        Object memberFactoryBean = applicationContext.getBean("&getMemberFactoryBean");
        System.out.println(getMemberFactoryBean.getClass());
        System.out.println(memberFactoryBean.getClass());

    }
}
