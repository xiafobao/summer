package com.xia.demo.config;

import com.xia.demo.annotation.RpcService;
import com.xia.demo.common.RpcDecoder;
import com.xia.demo.common.RpcEncoder;
import com.xia.demo.model.RpcRequest;
import com.xia.demo.model.RpcResponse;
import com.xia.demo.provider.BeanFactory;
import com.xia.demo.provider.ServerHandler;
import com.xia.demo.registory.RegistryServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author xiafb
 * @date Created in 2019/7/24 9:41
 * description
 * modified By
 * version
 */
@Slf4j
@Configuration
@ConditionalOnClass(RpcService.class)
public class ProviderAutoConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RpcProperties rpcProperties;

    /**
     * 这里注入bean
     */
    @PostConstruct
    public void init() {
        log.info("rpc server start scanning provider service... ");
        Map<String, Object> beanMap = this.applicationContext.getBeansWithAnnotation(RpcService.class);
        if (MapUtils.isNotEmpty(beanMap)) {
            beanMap.forEach(this::initProviderBean);
        }
        log.info("rpc server scan over...");
        // 如果有服务的话才启动netty server
        if (!beanMap.isEmpty()) {
            Thread thread = new Thread(() -> {
                startNetty(rpcProperties.getPort());
            });
            thread.start();
        }
    }

    /**
     * 将服务类交由BeanFactory管理
     *
     * @param beanName
     * @param beanObj
     */
    private void initProviderBean(String beanName, Object beanObj) {
        RpcService annotationOnBean = this.applicationContext.findAnnotationOnBean(beanName, RpcService.class);
        BeanFactory.addBean(annotationOnBean.value(), beanObj);
    }


    /**
     * 启动netty server
     *
     * @param port netty启动的端口
     */
    public void startNetty(int port) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new RpcDecoder(RpcRequest.class))
                            .addLast(new RpcEncoder(RpcResponse.class))
                            .addLast(new ServerHandler());
                }
            });
            bootstrap.option(ChannelOption.SO_BACKLOG, 128);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture sync = bootstrap.bind(port).sync();
            log.info("server start on prot: {}", port);
            //netty服务器启动后向zk注册该服务
            log.info(rpcProperties.getRegisterAddress());
            new RegistryServer(rpcProperties.getRegisterAddress(),
                    rpcProperties.getTimeout(), rpcProperties.getServerName(),
                    rpcProperties.getHost(), port)
                    .registry();
            sync.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("服务异常异常终止", e);
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

}
