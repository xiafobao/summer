package com.xia.demo.imports;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xiafb
 * @date Created in 2019/12/16 16:57
 * description
 * modified By
 * version
 */
public class ImportTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CarBeanConfig.class);
        String[] beanNamesForType = applicationContext.getBeanDefinitionNames();
        for (String name : beanNamesForType) {
            System.out.println(name);
        }
    }
}
