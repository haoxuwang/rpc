package com.tuling.framework.protocol.dubbo;

import com.tuling.framework.Invocation;
import com.tuling.framework.Protocol;
import com.tuling.framework.URL;

public class DubboProtocol implements Protocol {

    @Override
    public void start(URL url) {
        ChannelHandler channelHandler = new DispatcherHandler(new RequestHandler());

        NettyServer nettyServer = new NettyServer(channelHandler);
        nettyServer.start(url.getHostname(), url.getPort());

    }

    @Override
    public String send(URL url, Invocation invocation) {
        NettyClient nettyClient = new NettyClient();
        return nettyClient.send(url.getHostname(),url.getPort(), invocation);
    }
}
