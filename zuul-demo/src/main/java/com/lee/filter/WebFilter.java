package com.lee.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 由于经过zuul网关后请求头信息会丢失,所以需要在此处把头信息转发
 */
@Component
public class WebFilter extends ZuulFilter{
    @Override
    public String filterType() {
        //前置过滤器
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //取出request中的头信息
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String header = request.getHeader("headerName");
        //判空
        if (StringUtils.isEmpty(header)){
            //把请求头信息继续向下传递
            requestContext.addZuulRequestHeader("headerName",header);
        }
        return null;  //继续执行
    }
}
