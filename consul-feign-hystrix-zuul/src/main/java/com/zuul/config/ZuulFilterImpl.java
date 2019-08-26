package com.zuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ZuulFilterImpl extends ZuulFilter {

    /**
     * 过滤器类型pre表示在请求之前进行拦截
     * @return
     */
    @Override
    public String filterType() {
        //pre 在青丘被路由之前调用
        //route 在路由请求时被调用
        //post 在route和error过滤器之后被调用
        //error 处理请求发生错误时被调用
        return "pre";
    }

    /**
     * 过滤器的执行顺序，当请求在一个阶段存在多个过滤器时，需要依据该方法的返回值依次执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断过滤器是否生效
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //获取当前请求上下文
        RequestContext currentContex = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContex.getRequest();
        String userToken = request.getHeader("Authorization");
        if (StringUtils.isEmpty(userToken)) {
            currentContex.setSendZuulResponse(false);
            currentContex.setResponseStatusCode(401);
            currentContex.setResponseBody("userToken is null");
        }
        //否则正常执行业务逻辑
        return true;
    }
}
