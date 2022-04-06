package com.shf.pinda.zuul.filter;

import cn.hutool.core.util.StrUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.shf.pinda.base.R;
import com.shf.pinda.common.adapter.IgnoreTokenConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;

/**
 * 基础 网关过滤器
 */
@Slf4j
public abstract class BaseFilter extends ZuulFilter {
    @Value("${server.servlet.context-path}")
    protected String zuulPrefix;

    /**
     * 判断当前请求uri是否需要忽略，直接放行
     */
    protected boolean isIgnoreToken() {
//        动态获取当前请求的URI
        HttpServletRequest request =
                RequestContext.getCurrentContext().getRequest();
        String uri = request.getRequestURI();
        uri = StrUtil.subSuf(uri, zuulPrefix.length());
        uri = StrUtil.subSuf(uri, uri.indexOf("/", 1));
        boolean ignoreToken = IgnoreTokenConfig.isIgnoreToken(uri);
        return ignoreToken;
    }

    /**
     * 网关抛异常
     * @param errMsg
     * @param errCode
     * @param httpStatusCode
     */
    protected void errorResponse(String errMsg, int errCode, int httpStatusCode) {
        R tokenError = R.fail(errCode, errMsg);
        RequestContext ctx = RequestContext.getCurrentContext();
        // 返回错误码
        ctx.setResponseStatusCode(httpStatusCode);
        ctx.addZuulResponseHeader(
                "Content-Type", "application/json;charset=UTF-8");
        if (ctx.getResponseBody() == null) {
            // 返回错误内容
            ctx.setResponseBody(tokenError.toString());
            // 过滤该请求，不对其进行路由
            ctx.setSendZuulResponse(false);
        }
    }
}