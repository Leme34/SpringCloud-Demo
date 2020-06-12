package com.lee;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@EnableCircuitBreaker  //开启服务熔断
//@EnableDiscoveryClient  //springboot自动到配置文件的各种注册中心找服务
//@SpringBootApplication

@SpringCloudApplication  //已包含以上3个注解
@EnableFeignClients   //使用feign框架简化服务调用
public class ZipkinDemoApplication {

	//注入自带的http工具类restTemplate
	@Bean
	@LoadBalanced  //ribbon + restTemplate拦截器实现负载均衡
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(ZipkinDemoApplication.class, args);
	}
}
