package com.tuling.framework.protocol.dubbo;

import com.tuling.framework.Invocation;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class DispatcherHandler implements ChannelHandler {

    private ChannelHandler channelHandler;

    private ExecutorService executorService;

    public DispatcherHandler(ChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
        executorService = Executors.newFixedThreadPool(10);

    }

    @Override
    public void handler(ChannelHandlerContext ctx, Invocation invocation) throws Exception {
        System.out.println("线程池执行");
        executorService.execute(new ChannelEventRunnable(invocation, channelHandler, ctx));
    }
}
