package com.lee.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 获取注册的服务实例并测试
 */
@RestController
@RequestMapping("consumer")
@DefaultProperties(defaultFallback = "defaultFallback")  //此类中所有服务超时都由它处理
public class ConsumerController {

    //非负载均衡使用
    @Autowired
    private DiscoveryClient discoveryClient; //必须注入springframework包下的
    @Autowired
    private RestTemplate restTemplate;


    //若服务阻塞超时(失败)调用的方法,返回值必须与原方法相同
    /**  ==========================负载均衡调用服务============================== */
//    @GetMapping("{id}")
//    public TblEmp queryById(@PathVariable("id")Integer id){
//        //需要拉取的服务id
//        String serverId = "emp-service";
//        //1、非负载均衡写法
//        //根据服务id拉取(fetch)服务实例
////        List<ServiceInstance> instances = discoveryClient.getInstances(serverId);
//        //从实例中取出ip和端口号
////        ServiceInstance serviceInstance = instances.get(0);
////        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/emp/" + id;
//
//        //2、ribbon + restTemplate拦截器实现负载均衡(默认轮询)
//        // http:// + serverId + 请求url
//        String url = "http://"+ serverId +"/emp/" + id;
//        System.out.println("http(请求)调用该服务"+url);
//        //http(请求)调用该服务
//        TblEmp emp = restTemplate.getForObject(url, TblEmp.class);
//        return emp;
//    }




    @GetMapping("{id}")
    /**  ========================== 服务降级,调用自定义fallback方法返回失败信息 ============================== */
    //配置参数名称在HystrixPropertiesManager类中
    @HystrixCommand(fallbackMethod = "queryByIdFallback",commandProperties = {
            //配置此服务降级的最长等待时间,超时则fallback
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000"),
            /**  ============= 服务熔断,满足一下熔断条件时调用该服务的全都fallback ========== */
            //启用断路器
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            //一个统计窗口内熔断触发的最小个数,此处10s内请求10次失败则熔断服务
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            //间隔10s关闭1次断路器,若此时请求成功了则恢复服务,否则继续保持熔断,以此类推
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            //失败次数百分比占总次数50以上熔断
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50")
    })
    //若服务阻塞超时（默认是1000毫秒）调用的方法,返回值必须与原方法相同,因此返回json
    public String queryById(@PathVariable("id")Integer id){
        //若id超出范围则抛异常使服务熔断
        if (id>999){
            throw new RuntimeException("id不存在");
        }
        String serverId = "emp-service";
        String url = "http://"+ serverId +"/emp/" + id;
        //会自动把服务返回的对象序列化为json字符串
        String emp = restTemplate.getForObject(url, String.class);
        return emp;
    }
    public String queryByIdFallback(Integer id){
        return "sry,服务正忙!";
    }

    //全局服务超时处理方法,必须空参
    public String defaultFallback(){
        return "sry,服务正忙!";
    }

}
