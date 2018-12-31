package com.lee.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 开启了security后的eureka中必须通过此配置类关闭csrf，否则其他服务无法注册
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 高版本的丢弃了注解方式关闭csrf
     * security:
     *   basic:
     *    enabled: true
     * 所以要使用以下方式关闭csrf
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //注意：为了可以使用 http://${user}:${password}@${host}:${port}/eureka/ 这种方式登录,所以必须是httpBasic,
        // 如果是form方式,不能使用url格式登录
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }
}