package com.ilongli.sample.dubbo.consumer;

import com.ilongli.sample.dubbo.service.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @author ilongli
 * @date 2022/10/19 17:42
 */
@Service
public class DemoServiceRpc {

    @DubboReference
    private DemoService demoService;

    public String doSayHello(String name) {
        return demoService.sayHello(name);
    }

}
