package cn.com.benjiamin.api.gateway.Filters;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * created at 2021/9/15 16:37  sentinel_nacos
 * 自定义过滤器的案例，该案例是测试日志
 *
 */

//@Component
public class LogGatewayFilterFactory extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {

    /**
     * 构造器
     */
    public LogGatewayFilterFactory() {
        super(LogGatewayFilterFactory.Config.class);
    }

    /**
     * 读取配置文件application.yml中的参数  -Log=true,false
     *
     * @return
     */
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("consoleLog","cacheLog");
    }

    /**
     * 过滤器逻辑处理
     *
     * @param config
     * @return
     */
    public GatewayFilter apply(Config config) {

        return new GatewayFilter(){

            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                if(config.isConsoleLog()){
                    //
                    System.out.println("ConsoleLog日志开启");
                }
              if(config.isCacheLog()){
                  // 存在缓存日志
                  System.out.println("CacheLog缓存日志开启");
              }
                return chain.filter(exchange);

            }
        };
    }


    //内部类，姐搜配置参数
    public static class Config {
        public boolean consoleLog;
        public boolean cacheLog;

        public Config() {
        }

        public Config(boolean consoleLog, boolean cacheLog) {
            this.consoleLog = consoleLog;
            this.cacheLog = cacheLog;
        }

        public boolean isConsoleLog() {
            return consoleLog;
        }

        public void setConsoleLog(boolean consoleLog) {
            this.consoleLog = consoleLog;
        }

        public boolean isCacheLog() {
            return cacheLog;
        }

        public void setCacheLog(boolean cacheLog) {
            this.cacheLog = cacheLog;
        }
    }

}
