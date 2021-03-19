package com.jr.jd.creatorserviceweb.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: wanglei381
 * @Description: 后台跨域处理拦截器
 * @Date: Created in 5:13 PM 2019/3/29
 * @Modified By:
 */
@Slf4j
@Component
public class BackCrossHandlerInterceptor implements HandlerInterceptor {

    @Value("${creatorservice.corsUrl}")
    private String corsUrl;

    @Override
    public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Object handler) {
        String originUrl = servletRequest.getHeader("Origin");
        log.info("originUrl[{}],requestUrl[{}]", originUrl, servletRequest.getRequestURI());
        String uri = servletRequest.getRequestURI();
        if (null == originUrl) {
            return false;
        }
        if (corsUrl.indexOf(originUrl) >= 0) {
            servletResponse.setHeader("Access-Control-Allow-Origin", originUrl);
        } else {
            return false;
        }
        servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        servletResponse.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        servletResponse.addHeader("Access-Control-Allow-Headers", "DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range");
        servletResponse.setContentType("application/json;charset=utf-8");
        servletResponse.setCharacterEncoding("UTF-8");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

}
