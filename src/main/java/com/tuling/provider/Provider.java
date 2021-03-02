package com.tuling.provider;

import com.tuling.framework.URL;
import com.tuling.framework.protocol.NettyServer;
import com.tuling.framework.register.LocalRegister;
import com.tuling.framework.register.RemoteMapRegister;
import com.tuling.framework.register.ZookeeperRegister;
import com.tuling.provider.api.HelloService;
import com.tuling.provider.impl.HelloServiceImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Provider {

    public static void main(String[] args) throws UnknownHostException {

        String interfaceName = HelloService.class.getName();
        URL url = new URL(InetAddress.getLocalHost().getHostAddress(), 8081);

        LocalRegister.regist(interfaceName, HelloServiceImpl.class);
        ZookeeperRegister.regist(interfaceName, url);

        NettyServer nettyServer = new NettyServer();
        nettyServer.start(url.getHostname(), url.getPort());

        System.out.println(String.format("success, 成功暴露%s服务，地址为%s", interfaceName, url.toString()));
    }
}
