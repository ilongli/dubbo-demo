package com.ilongli.sample.dubbo.provider;

import com.ilongli.sample.dubbo.service.DemoService;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author ilongli
 * @date 2022/10/19 17:35
 */
@DubboService(version = "2.0")
public class DemoService2Impl implements DemoService {

    @Override
    public String sayHello(String name) {
        System.out.println("Hello " + name + ", request from consumer: " + RpcContext.getServiceContext().getRemoteAddress());
        return "[2]Hello " + name;
    }


    @Override
    public void sayHelloServerStream(String name, StreamObserver<String> response) {
        // 处理name
        response.onNext("hello:" + name);

        // 模拟业务处理时间
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 处理name
        response.onNext("hello:" + name);

        response.onCompleted();
    }

    @Override
    public StreamObserver<String> sayHelloStream(StreamObserver<String> response) {

        return new StreamObserver<String>() {

            @Override
            public void onNext(String data) {
                System.out.println("生产者接收到的数据：" + data);
                // 处理data，响应结果
                response.onNext("hello," + data);
                // 看时机，当数据响应完成后，调用response.onCompleted();
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
//                response.onCompleted();
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
                response.onCompleted();
            }
        };
    }
}
