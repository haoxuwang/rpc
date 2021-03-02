package com.tuling.framework.proxy;

import com.tuling.framework.LoadBalance;
import com.tuling.framework.URL;
import com.tuling.framework.protocol.Invocation;
import com.tuling.framework.protocol.NettyClient;
import com.tuling.framework.register.RemoteMapRegister;
import com.tuling.framework.register.ZookeeperRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProxyFactory<T> {

    @SuppressWarnings("unchecked")
    public static <T> T getProxy(final Class interfaceClass) {

        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);

                try {
                    NettyClient nettyClient = new NettyClient();

                    List<URL> urls = ZookeeperRegister.get(interfaceClass.getName());
                    URL url = LoadBalance.random(urls);

                    System.out.println("消费者选择的服务提供者地址是："+ url.toString());
                    String result = nettyClient.send(url.getHostname(), url.getPort(), invocation);
                    return result;
                } catch (Exception e) {
                    return doMock(invocation);
                }
            }
        });
    }

    private static Object doMock(Invocation invocation) {
        return "容错逻辑";
    }

}
