package com.ilongli.sample.dubbo.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDubbo
public class DubboProviderApplication {

    public static void main(String[] args) throws InterruptedException {
//        new EmbeddedZooKeeper(2181, false).start();
        SpringApplication.run(DubboProviderApplication.class, args);

/*        System.out.println("dubbo service started");
        new CountDownLatch(1).await();*/
    }

    @GetMapping("")
    public String hello() {
        return "hello-provider";
    }

}
