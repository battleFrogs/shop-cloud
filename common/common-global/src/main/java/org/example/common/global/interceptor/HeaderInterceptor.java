package org.example.common.global.interceptor;

import org.example.common.core.context.SecurityContextHolder;
import org.example.common.core.utils.JwtUtils;
import org.example.common.core.utils.ServletUtils;
import org.example.common.redis.token.*;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 自定义请求头拦截器，将Header数据封装到线程变量中方便获取
 * 注意：此拦截器会同时验证当前用户有效期自动刷新有效期
 */
@Component
public class HeaderInterceptor implements AsyncHandlerInterceptor {


    @Resource
    private TokenUtils tokenUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        SecurityContextHolder.setUserId(ServletUtils.getHeader(request, JwtUtils.USE_ID));
        SecurityContextHolder.setUserName(ServletUtils.getHeader(request, JwtUtils.USE_NAME));

//        LoginUser loginUser = tokenUtils.getLoginUser(SecurityContextHolder.getUserId());
//        tokenUtils.verifyToken(SecurityContextHolder.getUserId(), loginUser);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        SecurityContextHolder.remove();
    }
}
