package com.xia.demo.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author xiafb
 * @date Created in 2020/6/9 16:35
 * description
 * modified By
 * version
 */
public class JacksonTest {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        Person build = new Person();
        build.setName("张三");
        build.setAge(12);
        build.setSex("男");
        build.setRemark("fffffffffffffffffffffff");
        try {
            String s = objectMapper.writeValueAsString(build);
            System.out.println(s);
            Person person = objectMapper.readValue(s, Person.class);
            System.out.println(person.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
