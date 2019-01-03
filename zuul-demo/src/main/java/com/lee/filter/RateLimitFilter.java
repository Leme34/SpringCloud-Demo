package com.lee.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 限流过滤器示例，使用google实现好的令牌桶算法限流
 */
//@Component
public class RateLimitFilter extends ZuulFilter {

    //每秒创建100个令牌
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType() {
        //前置过滤器
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //过滤顺序优先级最高
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        //若获取不到令牌
        if (!RATE_LIMITER.tryAcquire()){
            ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
            ctx.setSendZuulResponse(false);
            //或手动抛出异常也可以拦截请求
        }
        return null;
    }

}
