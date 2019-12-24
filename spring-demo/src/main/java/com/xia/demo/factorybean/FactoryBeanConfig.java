package com.xia.demo.factorybean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiafb
 * @date Created in 2019/12/17 11:19
 * description
 * modified By
 * version
 */
@Configuration
public class FactoryBeanConfig {

    @Bean
    public MemberFactoryBean getMemberFactoryBean(){
        return new MemberFactoryBean();
    }
}
