//package cn.com.benjiamin.shoporder.config;
//
//import com.alibaba.cloud.commons.lang.StringUtils;
//import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * created at 2021/9/15 16:37  sentinel_nacos
// */
//
//@Component
//public class RequestOriginParserDefinition implements RequestOriginParser {
//    @Override
//    public String parseOrigin(HttpServletRequest httpServletRequest) {
//
//        String serviceName = httpServletRequest.getParameter("serviceName");
//        if(StringUtils.isEmpty(serviceName)){
//             throw new  RuntimeException("serviceName is empty");
//        }
//        return serviceName;
//    }
//}



