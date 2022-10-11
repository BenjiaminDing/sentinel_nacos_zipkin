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

    int i=0;
    /**
     * SentinelResource作用定义一个资源，还可以定义资源内部发生异常时候的处理逻辑
     * String blockHandler() default ""; 定义资源内部发生了BlockException应该进入的方法（捕获的是5种sentinel定义的异常
     * fallback   定义当前资源内部发生了Throwable 应该进入的方法
     *
     * @return
     */
    @SentinelResource(value = "message",
            blockHandlerClass = OrderServiceImpl3BlockHandler.class,
            blockHandler = "blockHandler1",
            fallbackClass=OrderServiceImpl3Fallback.class,
            fallback="fallback1")
    public String SentinelResourceService(String carNo) {

        i++;
        if (i % 3 == 0) {
            throw new RuntimeException("0.333大于阈值0.25");
        }
        return  " @SentinelResource学习{}，参数：："+carNo;
    }








}
