package com.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.context.annotation.FilterType;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter{
    /**
     * pre：可以在请求被路由之前调用。
     * routing：在路由请求时候被调用。
     * post：在routing和error过滤器之后被调用。
     * error：处理请求时发生错误时被调用。
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 通过int值来定义过滤器的执行顺序，数值越小优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 返回一个boolean类型来判断该过滤器是否要执行。我们可以通过此方法来指定过滤器的有效范围。
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑。在该函数中，我们可以实现自定义的过滤逻辑，来确定是否要拦截当前的请求，不对其进行后续的路由，或是在请求路由返回结果之后，对处理结果做一些加工等。
     * @return
     */
    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        System.out.println(String.format("%s AccessPasswordFilter request to %s", request.getMethod(), request.getRequestURL().toString()));
        return null;
    }
}
