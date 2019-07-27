package com.xia.demo.consummer;

import com.xia.demo.model.ProviderInfo;
import com.xia.demo.model.RpcRequest;
import com.xia.demo.model.RpcResponse;
import com.xia.demo.registory.ServiceDiscovery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * @author xiafb
 * @date Created in 2019/7/26 10:22
 * description
 * modified By
 * version
 */
@Component
public class RpcProxy {
    @Autowired
    private ServiceDiscovery serviceDiscovery;

    @SuppressWarnings("unchecked")
    public <T> T create(Class<?> interfaceClass, String providerName) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass}, (proxy, method, args) -> {
            RpcRequest rpcRequest = new RpcRequest();
            rpcRequest.setRequestId(UUID.randomUUID().toString())
                    .setClassName(method.getDeclaringClass().getName())
                    .setMethodName(method.getName())
                    .setParamTypes(method.getParameterTypes())
                    .setParams(args);
            ProviderInfo providerInfo = serviceDiscovery.discover(providerName);
            String[] addrInfo = providerInfo.getAddr().split(":");
            String host = addrInfo[0];
            int port = Integer.parseInt(addrInfo[1]);
            RpcClient rpcClient = new RpcClient(host, port);
            RpcResponse response = rpcClient.send(rpcRequest);
            if (response.isError()) {
                throw response.getError();
            } else {
                return response.getResult();
            }
        });
    }


    public void setServiceDiscovery(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }
}
