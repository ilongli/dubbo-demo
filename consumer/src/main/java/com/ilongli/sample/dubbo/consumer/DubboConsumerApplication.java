package com.ilongli.sample.dubbo.consumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SpringBootApplication
@RestController
@EnableDubbo
public class DubboConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }

    @Resource
    private DemoServiceRpc demoServiceRpc;

    @GetMapping("")
    public String hello(String name) {
        return demoServiceRpc.doSayHello(name);
    }

    @GetMapping("stream")
    public String helloStream(String name) {
        return demoServiceRpc.doSayHelloStream(name);
    }

    @GetMapping("stream2")
    public String helloStream2(String name) {
        return demoServiceRpc.doSayHelloStream2(name);
    }

}
