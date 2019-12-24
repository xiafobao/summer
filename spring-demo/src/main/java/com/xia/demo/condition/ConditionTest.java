package com.xia.demo.condition;

import com.xia.demo.bean.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xiafb
 * @date Created in 2019/12/16 16:40
 * description
 * modified By
 * version
 */
public class ConditionTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PersonBeanConfig.class);
        String[] beanNamesForType = applicationContext.getBeanNamesForType(Car.class);
        for (String name : beanNamesForType) {
            System.out.println(name);
        }
    }
}
