package org.example.common.global.config;

import org.example.common.global.interceptor.HeaderInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 拦截器配置
 *
 * @author ruoyi
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private HeaderInterceptor headerInterceptor;

    /**
     * 不需要拦截地址
     */
    public static final String[] excludeUrls = {"/login", "/logout", "/refresh"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(headerInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludeUrls)
                .order(-10);
    }

}
