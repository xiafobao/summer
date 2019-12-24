package com.xia.demo.condition;

import com.xia.demo.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiafb
 * @date Created in 2019/12/16 16:40
 * description
 * modified By
 * version
 */
@Configuration
public class PersonBeanConfig {

    @Conditional(ConditionA.class)
    @Bean
    public Person man(){
        Person person = new Person();
        person.setName("张三");
        person.setSex("男");
        return person;
    }

    @Conditional(ConditionB.class)
    @Bean
    public Person woman(){
        Person person = new Person();
        person.setName("张三");
        person.setSex("男");
        return person;
    }
}
