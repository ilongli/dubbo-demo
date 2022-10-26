package com.ilongli.sample.dubbo.consumer;

import com.ilongli.sample.dubbo.service.DemoService;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @author ilongli
 * @date 2022/10/19 17:42
 */
@Service
public class DemoServiceRpc {

    @DubboReference(version = "2.0")
    private DemoService demoService;

    public String doSayHello(String name) {
        return demoService.sayHello(name);
    }

    public String doSayHelloStream(String name) {

        demoService.sayHelloServerStream(name, new StreamObserver<String>() {

            @Override
            public void onNext(String data) {
                System.out.println("[ServerStream]接收到结果: " + data);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("[ServerStream]结束");
            }
        });

        return null;
    }


    public String doSayHelloStream2(String name) {

        StreamObserver<String> streamObserver = demoService.sayHelloStream(new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                System.out.println("消费者接收到的数据：" + data);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }
        });

        streamObserver.onNext("111:" + name);
        streamObserver.onNext("222:" + name);
        streamObserver.onNext("333:" + name);
        streamObserver.onCompleted();

        return null;
    }
}
