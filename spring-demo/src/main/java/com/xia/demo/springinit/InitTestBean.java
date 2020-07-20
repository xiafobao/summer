package com.xia.demo.springinit;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author xiafb
 * @date Created in 2020/7/11 10:26
 * description bean加载测试类
 * modified By
 * version  1.0
 */
public class InitTestBean implements InitializingBean, BeanDefinitionRegistryPostProcessor, ApplicationContextAware, ApplicationListener<ApplicationEvent> {

    @Override
    public void afterPropertiesSet() {
        System.out.println("实现InitializingBean类重写。。。afterPropertiesSet方法");
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        System.out.println("实现ApplicationListener类重写。。。onApplicationEvent方法");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        System.out.println("实现BeanDefinitionRegistryPostProcessor类重写。。。postProcessBeanDefinitionRegistry方法");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("实现BeanDefinitionRegistryPostProcessor类重写。。。postProcessBeanFactory方法");
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("getId:"+applicationContext.getId());
        System.out.println("getApplicationName:"+applicationContext.getApplicationName());
        System.out.println("getDisplayName:"+applicationContext.getDisplayName());
        System.out.println("实现ApplicationContextAware类重写。。。setApplicationContext方法");
    }
}
