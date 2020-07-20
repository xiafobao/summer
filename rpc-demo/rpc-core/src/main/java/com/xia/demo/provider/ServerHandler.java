package com.xia.demo.provider;

import com.xia.demo.model.RpcRequest;
import com.xia.demo.model.RpcResponse;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author xiafb
 * @date Created in 2019/7/26 11:06
 * description
 * modified By
 * version
 */
@Slf4j
public class ServerHandler extends SimpleChannelInboundHandler<RpcRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest msg) throws Exception {
        log.info("provider accept request,{}", msg);
        // 返回的对象。
        RpcResponse response = new RpcResponse();
        response.setRequestId(msg.getRequestId());
        try {
            Object handle = handle(msg);
            response.setResult(handle);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setError(ex);
        }
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("netty provider caught error,", cause);
        ctx.close();
    }

    private Object handle(RpcRequest request) throws Exception {
        String className = request.getClassName();
        Class<?> aClass = Class.forName(className);
        Object bean = BeanFactory.getBean(aClass);
        String methodName = request.getMethodName();
        Class<?>[] paramTypes = request.getParamTypes();
        Object[] params = request.getParams();
        Method method = aClass.getMethod(methodName, paramTypes);
        Object invoke = method.invoke(bean, params);
        return invoke;
    }
}
