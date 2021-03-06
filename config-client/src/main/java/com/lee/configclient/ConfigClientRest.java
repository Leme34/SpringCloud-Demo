package com.lee.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RefreshScope
public class ConfigClientRest {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaServer;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/config")
    public String getConfig() {
        System.out.println("applicationName=" + applicationName + " ,eurekaServer=" + eurekaServer + " ,port=" + port);
        return "applicationName=" + applicationName + " ,eurekaServer=" + eurekaServer + " ,port=" + port;
    }


}
