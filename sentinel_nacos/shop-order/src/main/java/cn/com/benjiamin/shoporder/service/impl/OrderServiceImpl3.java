package cn.com.benjiamin.shoporder.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * created at 2021/9/15 16:37  sentinel_nacos
 */
@Service
public class OrderServiceImpl3 {

//       @SentinelResource定义资源的
    @SentinelResource("message")
        public String message() {

        return "链路测试";
            }
}
