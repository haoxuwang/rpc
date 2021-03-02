package com.tuling.framework.protocol.dubbo;

import com.tuling.framework.Invocation;
import io.netty.channel.ChannelHandlerContext;

public class ChannelEventRunnable implements Runnable {

    private Invocation invocation;
    private ChannelHandler channelHandler;
    private ChannelHandlerContext ctx;


    public ChannelEventRunnable(Invocation invocation, ChannelHandler channelHandler, ChannelHandlerContext ctx) {
        this.invocation = invocation;
        this.channelHandler = channelHandler;
        this.ctx = ctx;
    }

    @Override
    public void run() {
        try {
            channelHandler.handler(ctx, invocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
