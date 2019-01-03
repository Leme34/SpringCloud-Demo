package com.lee;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@EnableCircuitBreaker  //开启服务熔断
//@EnableDiscoveryClient  //springboot自动到配置文件的各种注册中心找服务
//@SpringBootApplication

@SpringCloudApplication  //已包含以上3个注解
public class ConsumerDemoApplication {

	//注入自带的http工具类restTemplate
	@Bean
	@LoadBalanced  //ribbon + restTemplate拦截器实现负载均衡
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerDemoApplication.class, args);
	}
}
