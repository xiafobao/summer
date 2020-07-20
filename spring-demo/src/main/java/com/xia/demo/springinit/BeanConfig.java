package com.xia.demo.springinit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiafb
 * @date Created in 2020/7/11 10:24
 * description 配置bean等同于spring.xml中的configuration标签中的内容
 * modified By
 * version  1.0
 */
@Configuration
public class BeanConfig {

    @Bean
    public InitTestBean initTestBean(){
        return new InitTestBean();
    }

}
