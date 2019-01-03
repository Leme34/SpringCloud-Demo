package com.lee.client;

import com.lee.pojo.TblEmp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign的客户端: Feign整合了Ribbon和Hystrix
 * 在此定义与ConsumerController相同的接口,调用empClient.XXX()时feign会自动负载均衡拉取服务
 */

//value = 需要调用的微服务的serverId ,fallback = feign的熔断default处理类,fallbackFactory = feign的熔断接口统一实现类
//@FeignClient(value = "emp-service",fallback = EmpClientDeafultFallback.class)
@FeignClient(value = "emp-service",fallbackFactory = EmpClientFallbackFactory.class)
public interface EmpClient {

    @GetMapping("/emp/{id}")
    TblEmp queryById(@PathVariable("id") Integer id);

}
