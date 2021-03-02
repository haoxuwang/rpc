package com.tuling.framework.protocol.dubbo;

import com.tuling.Invoker;
import com.tuling.framework.*;
import com.tuling.framework.register.RemoteMapRegister;

import java.util.List;

public class DubboInvoker implements Invoker {



    @Override
    public String invoke(Invocation invocation) {

        List<URL> urlList = RemoteMapRegister.get(invocation.getInterfaceName());
        URL url = LoadBalance.random(urlList, null);
        Protocol protocol = ProtocolFactory.getProtocol();
        String result = protocol.send(url, invocation);
        return result;
    }
}
