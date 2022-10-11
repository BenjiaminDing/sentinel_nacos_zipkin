package cn.com.benjiamin.api.gateway.predicates;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * created at 2021/9/15 16:37  sentinel_nacos
 * 这是一个自定义路由断言的工厂类，
 * 要求两个： 第一名字必须是配置{Age}+RoutePredicateFactory,
 * 第二个要求：必须继承AbstractRoutePredicateFactory<配置类>
 */
//@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {

    public AgeRoutePredicateFactory() {

        super(AgeRoutePredicateFactory.Config.class);
    }

    /**
     * 读取配置文件中的参数值，给他赋值到配置类的属性中
     *
     * @return
     */
    public List<String> shortcutFieldOrder() {

        return Arrays.asList("minAge", "maxAge");
    }

    /**
     * 断言逻辑
     *
     * @param config
     * @return
     */
    public Predicate<ServerWebExchange> apply(AgeRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {

            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                // 接收前台传入的args参数
          String ageStr=      serverWebExchange.getRequest().getQueryParams().getFirst("age");
                //先判断是否为null
                if(StringUtils.isNotEmpty(ageStr)){
                    int age=Integer.parseInt(ageStr);
                    if(age>config.getMinAge()&&age<config.getMaxAge()) {
                        return true;
                    }else {
                        return false;
                    }
                }
                //不是null进行路由判断
                return false;
            }
        };

    }


    /**
     * 用于接收application.yml配置文件中的对应参数
     */
// 内部类
        @Data
        @NoArgsConstructor
    public static class Config {
        private Integer minAge;
        private Integer maxAge;

    }

}


