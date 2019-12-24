package com.xia.demo.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiafb
 * @date Created in 2019/12/16 16:26
 * description
 * modified By
 * version
 */
@Configuration
public class ConfigBean {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Person getPerson(){
        Person person = new Person();
        person.setName("张三");
        person.setSex("男");
        return person;
    }
}
