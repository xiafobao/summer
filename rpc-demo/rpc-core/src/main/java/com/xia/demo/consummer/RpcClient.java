package com.xia.demo.consummer;

import com.xia.demo.common.RpcDecoder;
import com.xia.demo.common.RpcEncoder;
import com.xia.demo.model.RpcRequest;
import com.xia.demo.model.RpcResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * @author xiafb
 * @date Created in 2019/7/26 10:30
 * description
 * modified By
 * version
 */
@Slf4j
public class RpcClient extends SimpleChannelInboundHandler<RpcResponse> {

    private String host;

    private int port;

    private CompletableFuture<String> future;

    private RpcResponse rpcResponse;

    public RpcClient(String host, int port) {
        this.host = host;
        this.port = port;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponse msg) throws Exception {
        log.info("client get request, {}", msg);
        this.rpcResponse = msg;
        future.complete("");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("netty client caught exception,", cause);
        ctx.close();
    }

    public RpcResponse send(RpcRequest rpcRequest) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(worker);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast(new RpcEncoder(RpcRequest.class))
                            .addLast(new RpcDecoder(RpcResponse.class))
                            .addLast(RpcClient.this);
                }
            });
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture sync = bootstrap.connect(host, port).sync();
            sync.channel().writeAndFlush(rpcRequest).sync();
            future = new CompletableFuture<>();
            future.get();
            if (rpcResponse == null) {
                sync.channel().closeFuture().sync();
            }
            return rpcResponse;
        } catch (Exception e) {
            log.error("client send msg error,", e);
            return null;
        } finally {
            worker.shutdownGracefully();
        }
    }

}
