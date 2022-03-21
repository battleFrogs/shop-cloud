package org.example.gateway.filter;

import org.apache.commons.lang3.StringUtils;
import org.example.gateway.properties.WhiteProperties;
import org.example.gateway.utils.ResponseUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse response = exchange.getResponse();
        String path = exchange.getRequest().getURI().getPath();

        // 白名单地址过滤
        List<String> whiteAddress = whiteProperties.getAddress();
        if (whiteAddress.contains(path)) {
            chain.filter(exchange);
        }

        // 判断请求头是否有token
        String authToken = exchange.getRequest().getHeaders().getFirst("Auth-Token");
        if (StringUtils.isBlank(authToken)) {
            return ResponseUtils.webFluxResponseWriter(response, MediaType.APPLICATION_JSON_VALUE,
                    HttpStatus.INTERNAL_SERVER_ERROR, "没有请求头", 500);
        }


        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
