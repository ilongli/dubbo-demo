package com.ilongli.sample.dubbo.service;

import org.apache.dubbo.common.stream.StreamObserver;

/**
 * @author ilongli
 * @date 2022/10/19 17:35
 */
public interface DemoService {

    // UNARY
    String sayHello(String name);

    // SERVER_STREAM
    default void sayHelloServerStream(String name, StreamObserver<String> response) {

    }

    // CLIENT_STREAM / BI_STREAM
    default StreamObserver<String> sayHelloStream(StreamObserver<String> response) {
        return response;
    }

}
