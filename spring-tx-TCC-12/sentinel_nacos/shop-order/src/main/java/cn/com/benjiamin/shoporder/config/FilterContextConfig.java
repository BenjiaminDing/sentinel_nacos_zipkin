package cn.com.benjiamin.shoporder.config;

import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created at 2021/9/15 16:37  sentinel_nacos
 */
//@Configuration
public class FilterContextConfig {
//    @Bean
//        public FilterRegistrationBean sentinelFilterRegistration() {
////        FilterRegistrationBean registration = new FilterRegistrationBean();
////        registration.setFilter(new CommonFilter());
////        registration.addUrlPatterns("/*");
////
////        // 入口资源关闭聚合
////        registration.addInitParameter(, "false");
////        registration.setName("sentinelFilter");
////        registration.setOrder(1);
////        return  registration;
//            }

}
/*
    @Configuration
    public class FilterContextConfig {
  @Bean
  public FilterRegistrationBean sentinelFilterRegistration() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(new CommonFilter());
    registration.addUrlPatterns("/*");
    // 入口资源关闭聚合
    registration.addInitParameter(CommonFilter.WEB_CONTEXT_UNIFY, "false");
    registration.setName("sentinelFilter");
    registration.setOrder(1);
    return registration;
 */
