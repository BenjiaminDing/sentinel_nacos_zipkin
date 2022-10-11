package cn.com.benjiamin.shoporder.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * created at 2021/9/15 16:37  sentinel_nacos
 */
@Service
@Slf4j
public class OrderServiceImpl3 {

    @SentinelResource(value = "message")
    public String messageService() {
        System.out.println("链路流控测试");
        return "》》链路流控测试";
    }



    











}
