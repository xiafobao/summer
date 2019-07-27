package com.xia.demo.config;

import com.xia.demo.annotation.RpcConsumer;
import com.xia.demo.consummer.RpcProxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;

/**
 * @author xiafb
 * @date Created in 2019/7/26 10:56
 * description
 * modified By
 * version
 */
@Configuration
@ConditionalOnClass(RpcConsumer.class)
@EnableConfigurationProperties(RpcProperties.class)
public class ConsumerAutoConfiguration {
    @Autowired
    private RpcProxy rpcProxy;

    /**
     * 设置动态代理
     */
    @Bean
    public BeanPostProcessor beanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                Class<?> aClass = bean.getClass();
                for (Field field : aClass.getDeclaredFields()) {
                    RpcConsumer rpcConsumer = field.getAnnotation(RpcConsumer.class);
                    if (null != rpcConsumer) {
                        Class<?> type = field.getType();
                        field.setAccessible(true);
                        try {
                            field.set(bean, rpcProxy.create(type, rpcConsumer.providerName()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            field.setAccessible(false);
                        }
                    }
                }
                return bean;
            }
        };
    }
}
