package com.tuling.framework;

import com.tuling.Invoker;
import com.tuling.framework.protocol.dubbo.ClusterInvoker;
import com.tuling.framework.protocol.dubbo.DubboInvoker;
import com.tuling.framework.protocol.dubbo.MockInvoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory<T> {

    @SuppressWarnings("unchecked")
    public static <T> T getProxy(final Class interfaceClass) {

        Invoker invoker = new MockInvoker(new ClusterInvoker());

        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);

                String result = invoker.invoke(invocation);

                return result;
            }
        });
    }

}
