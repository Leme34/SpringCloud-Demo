package com.lee.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 后置过滤器示例，服务调用完成后添加返回头信息
 */
@Component
public class addRepsonseHeadFilter extends ZuulFilter {

    @Override
    public String filterType() {
        //后置过滤器
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        //顺序在返回响应过滤器之前
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse response = ctx.getResponse();
        response.setHeader("X-TEST", String.valueOf(new Date()));
        return null;
    }
}
