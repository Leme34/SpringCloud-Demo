package com.lee.consumerhystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard  //开启hystrix监控
public class ConsumerHystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerHystrixDashboardApplication.class, args);
	}
}
