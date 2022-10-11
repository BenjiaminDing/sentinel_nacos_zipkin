package cn.com.benjiamin.api.gateway.Filters;

import com.alibaba.cloud.commons.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * created   AuthGlobalFilter  by benjiamin at 2022/6/3 / 10:18 /18
 * 必须实现两个接口，实现两个方法
 */
@Slf4j
//@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    // 编写过滤器的逻辑
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //
        String token=exchange.getRequest().getQueryParams().getFirst("token");
        if(!StringUtils.equals(token,"admin")){
            //认证失败
            log.info("认证失败");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    //标识当前过滤器的优先级,优先级越小优先级越高
    public int getOrder() {
        return 0;
    }
}
