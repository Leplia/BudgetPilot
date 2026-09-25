package org.budget.apigateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import org.budget.apigateway.util.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtTokenProvider tokenValidator;

    public JwtAuthenticationFilter(JwtTokenProvider tokenValidator) {
        this.tokenValidator = tokenValidator;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().value();

        if (isPublicEndpoint(path)) {
            log.debug("Публичный эндпоинт, пропускаем проверку JWT: {}", path);
            return chain.filter(exchange);
        }

        String token = extractTokenFromHeader(request);

        if (token == null) {
            log.warn("Отсутствует JWT для защищённого эндпоинта: {}", path);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        try {
            Jws<Claims> jws = tokenValidator.verify(token);
            Claims claims = jws.getBody();

            if (!"access".equals(claims.get("type", String.class))) {
                log.warn("Ожидался access-токен, path: {}", path);
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String userId = claims.getSubject();
            String login = claims.get("login", String.class);

            log.info("Аутентифицирован пользователь: {} (ID: {})", login, userId);

            ServerHttpRequest mutatedRequest = request.mutate()
                    .headers(headers -> {
                        headers.remove("X-User-Id");
                        headers.remove("X-User-Login");
                        headers.set("X-User-Id", userId);
                        headers.set("X-User-Login", login);
                    })
                    .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build());

        } catch (JwtException ex) {
            log.warn("Ошибка валидации JWT для path {}: {}", path, ex.getMessage());
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        } catch (Exception ex) {
            log.error("Непредвиденная ошибка при аутентификации, path {}: {}", path, ex.getMessage(), ex);
            exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return exchange.getResponse().setComplete();
        }
    }

    private String extractTokenFromHeader(ServerHttpRequest request) {
        String header = request.getHeaders().getFirst("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    private boolean isPublicEndpoint(String path) {
        return path.startsWith("/api/auth/login")
                || path.startsWith("/api/auth/register")
                || path.startsWith("/api/auth/refresh");
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
