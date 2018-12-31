package com.lee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//开启Zuul的网关功能,zuul能代理一切微服务,客户访问网关由网关跳转指定微服务
//注意：经过网关的request中的header信息会丢失
@EnableZuulProxy
@SpringBootApplication
public class ZuulDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulDemoApplication.class,args);
    }
}
