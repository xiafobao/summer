package com.xia.demo.registerbeandefinitions;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author xiafb
 * @date Created in 2019/12/16 17:19
 * description
 * modified By
 * version
 */
public class MyRegisterBeanDefinitions implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition("com.xia.demo.registerbeandefinitions.RainBow");
        registry.registerBeanDefinition("rainBow", rootBeanDefinition);
    }
}
