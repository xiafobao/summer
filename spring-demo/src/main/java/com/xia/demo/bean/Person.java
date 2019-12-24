package com.xia.demo.bean;

import lombok.Data;

/**
 * @author xiafb
 * @date Created in 2019/12/16 16:26
 * description
 * modified By
 * version
 */
@Data
public class Person {
    String name;
    String sex;

    public Person(){
        System.out.println("创建person对象");
    }

    public void init(){
        System.out.println("初始化person对象");
    }

    public void destroy(){
        System.out.println("销毁person对象");
    }
}
