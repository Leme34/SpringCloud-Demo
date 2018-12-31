package com.lee.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 通过zuul实现自定义拦截器,若请求没有传参access-token则拦截请求
 */
@Component
public class LoginFilter extends ZuulFilter{

    /**
     * 指定过滤器类型
     */
    @Override
    public String filterType() {
        //前置过滤器
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 指定过滤器顺序,数字越小表示越先执行
     */
    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;  //放在处理请求头之前
    }

    /**
     * 是否开启当前过滤器,true表示开启
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤逻辑
     * @return return任何值(包括null)都表示继续执行
     *  只有  RequestContext ctx = RequestContext.getCurrentContext();
     *          ctx.setSendZuulResponse(false);
     *  后才不继续执行
     */
    @Override
    public Object run() throws ZuulException {
        //获取zuul的请求上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //获取请求域中的数据
        String token = request.getParameter("access-token");
        //若不存在
        if (StringUtils.isBlank(token)){
            //未登录,拦截请求
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.SC_FORBIDDEN);
        }
        //已登录,放行(不拦截即放行)
        return null;
    }
}
