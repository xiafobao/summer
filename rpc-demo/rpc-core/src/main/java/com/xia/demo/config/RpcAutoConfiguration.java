package com.xia.demo.config;

import com.xia.demo.consummer.RpcProxy;
import com.xia.demo.exception.ZkConnectException;
import com.xia.demo.registory.ServiceDiscovery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiafb
 * @date Created in 2019/7/24 14:57
 * description
 * modified By
 * version
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(RpcProperties.class)
public class RpcAutoConfiguration {
    @Autowired
    private RpcProperties rpcProperties;


    @Bean
    @ConditionalOnMissingBean
    public ServiceDiscovery serviceDiscovery() {
        ServiceDiscovery serviceDiscovery = null;
        try {
            serviceDiscovery = new ServiceDiscovery(rpcProperties.getRegisterAddress());
        } catch (ZkConnectException ex) {
            log.error("zk connect fail", ex);
        }
        return serviceDiscovery;
    }

    @Bean
    @ConditionalOnMissingBean
    public RpcProxy rpcPoxy() {
        RpcProxy rpcPoxy = new RpcProxy();
        rpcPoxy.setServiceDiscovery(serviceDiscovery());
        return rpcPoxy;
    }
}
