package com.tuling.framework.protocol;

import com.tuling.framework.register.LocalRegister;
import io.netty.channel.ChannelHandlerContext;

import java.lang.reflect.Method;

public class RequestHandler implements ChannelHandler {

    @Override
    public void handler(ChannelHandlerContext ctx, Invocation invocation) throws Exception{

        Class serviceImpl = LocalRegister.get(invocation.getInterfaceName());

        Method method = serviceImpl.getMethod(invocation.getMethodName(), invocation.getParamTypes());
        Object result = method.invoke(serviceImpl.newInstance(), invocation.getParams());

        ctx.writeAndFlush("Netty:" + result);
    }
}
