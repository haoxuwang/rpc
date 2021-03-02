package com.tuling.framework.protocol.dubbo;

import com.tuling.Invoker;
import com.tuling.framework.*;
import com.tuling.framework.register.RemoteMapRegister;

import java.util.ArrayList;
import java.util.List;

public class ClusterInvoker implements Invoker {


    @Override
    public String invoke(Invocation invocation) {
        int retries = 3;
        List<URL> selectedList = new ArrayList();

        for (int i=0; i<retries; i++) {

            List<URL> urlList = RemoteMapRegister.get(invocation.getInterfaceName());
            URL url = LoadBalance.random(urlList, selectedList);
            selectedList.add(url);

            try {
                Protocol protocol = ProtocolFactory.getProtocol();
                String result = protocol.send(url, invocation);
                return result;
            } catch (Exception e) {
                System.out.println("重试");
            }
        }

        return null;
    }
}
