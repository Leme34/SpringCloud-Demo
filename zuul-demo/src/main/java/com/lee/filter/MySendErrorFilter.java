package com.lee.filter;

import com.google.gson.JsonObject;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理出错请求
 */
@Slf4j
@Component
public class MySendErrorFilter extends SendErrorFilter {
    @Override
    public String filterType() {
        //错误过滤器
        return FilterConstants.ERROR_TYPE;
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
    public Object run() {
        RequestContext reqContext = RequestContext.getCurrentContext();
        ExceptionHolder exceptionHolder = findZuulException(reqContext.getThrowable());
        Throwable throwable = exceptionHolder.getThrowable();
        // 获取状态码
        int statusCode = reqContext.getResponseStatusCode();
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("code", statusCode);
        jsonObj.addProperty("errorMessage", throwable.getMessage());
        // 记录日志
        log.warn("出现异常", throwable);
        reqContext.setResponseBody(jsonObj.toString());
        reqContext.getResponse().setContentType("text/html;charset=UTF-8");
        // 处理完后清空请求域的异常
        reqContext.remove("throwable");
        return null;  //继续执行
    }
}
