package com.tuling.framework.protocol.dubbo;

import com.tuling.Invoker;
import com.tuling.framework.Invocation;

public class MockInvoker implements Invoker {

    private Invoker invoker;

    public MockInvoker(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public String invoke(Invocation invocation) {

        String mock = System.getProperty("mock");
        if (mock != null && mock.startsWith("force:")) {
            String result = mock.replace("force:return:", "");
            return result;
        }

        try {
            String result = invoker.invoke(invocation);
            return result;
        } catch (Exception e) {
            if (mock != null && mock.startsWith("fail:")) {
                String result = mock.replace("fail:return:", "");
                return result;
            }
        }

        return null;
    }
}
