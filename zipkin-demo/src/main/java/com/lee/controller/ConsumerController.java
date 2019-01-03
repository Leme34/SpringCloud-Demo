package com.lee.controller;

import com.lee.client.EmpClient;
import com.lee.pojo.TblEmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取注册的服务实例并测试
 */
@RestController
@RequestMapping("consumer")
//@DefaultProperties(defaultFallback = "queryByIdFallback")  //此类中所有服务超时都由它处理
public class ConsumerController {

    /**  ==========整合feign框架调用服务,服务降级和熔断在FeignClient中声明============================== */
    @Autowired
    private EmpClient empClient;
    @GetMapping("{id}")
    public TblEmp queryById(@PathVariable("id")Integer id,HttpServletRequest request){
        //feign客户端会自动负载均衡拉取服务
        return empClient.queryById(id);
    }

}
