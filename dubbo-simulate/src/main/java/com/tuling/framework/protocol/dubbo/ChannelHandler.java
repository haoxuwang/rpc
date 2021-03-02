package com.tuling.framework.protocol.dubbo;

import com.tuling.framework.Invocation;
import io.netty.channel.ChannelHandlerContext;

public interface ChannelHandler {

    public void handler(ChannelHandlerContext ctx, Invocation invocation) throws Exception;

}
