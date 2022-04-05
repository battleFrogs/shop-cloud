package org.example.gateway.filter;

import org.apache.commons.lang3.StringUtils;
import org.example.common.core.utils.JwtUtils;
import org.example.common.core.utils.ServletUtils;
import org.example.common.redis.token.*;
import org.example.gateway.properties.WhiteProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Resource
    private WhiteProperties whiteProperties;
    @Resource
    private TokenUtils tokenUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest.Builder mutate = request.mutate();
        String path = request.getURI().getPath();

        // 白名单地址过滤
        List<String> whiteAddress = whiteProperties.getAddress();
        if (whiteAddress.contains(path)) {
            return chain.filter(exchange);
        }

        // 判断请求头是否有token
        String authToken = exchange.getRequest().getHeaders().getFirst("Auth-Token");
        if (StringUtils.isBlank(authToken)) {
            return ServletUtils.webFluxResponseWriter(response,
                    HttpStatus.INTERNAL_SERVER_ERROR, "没有请求头", 500);
        }

        // 校验信息是否完整
        String userId = JwtUtils.getUserId(authToken);
        String useName = JwtUtils.getUseName(authToken);
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(useName)) {
            return ServletUtils.webFluxResponseWriter(response,
                    HttpStatus.INTERNAL_SERVER_ERROR, "信息为空", 500);
        }

        // 校验登录是否过期
        LoginUser loginUser = tokenUtils.getLoginUser(Long.valueOf(userId));
        if (loginUser == null) {
            return ServletUtils.webFluxResponseWriter(response,
                    HttpStatus.INTERNAL_SERVER_ERROR, "登录过期", 401);
        }
        if (!loginUser.getToken().equals(authToken)) {
            return ServletUtils.webFluxResponseWriter(response,
                    HttpStatus.INTERNAL_SERVER_ERROR, "token不一致", 500);
        }
        // 如果快过期的时候刷新
        tokenUtils.verifyToken(Long.valueOf(userId), loginUser);

        addHeader(mutate, JwtUtils.USE_ID, userId);
        addHeader(mutate, JwtUtils.USE_NAME, useName);

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }


    private void addHeader(ServerHttpRequest.Builder mutate, String name, Object value) {
        if (value == null) {
            return;
        }
        String valueStr = value.toString();
        String valueEncode = ServletUtils.urlEncode(valueStr);
        mutate.header(name, valueEncode);
    }

}
