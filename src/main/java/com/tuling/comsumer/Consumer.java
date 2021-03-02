package com.tuling.comsumer;

import com.tuling.framework.protocol.Invocation;
import com.tuling.framework.protocol.NettyClient;
import com.tuling.framework.proxy.ProxyFactory;
import com.tuling.provider.api.HelloService;

public class Consumer {

    public static void main(String[] args) {

        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        for (int i = 0; i < 10; i++) {
            String result = helloService.sayHello("周瑜大大111");
            System.out.println(result);
        }

    }
}
