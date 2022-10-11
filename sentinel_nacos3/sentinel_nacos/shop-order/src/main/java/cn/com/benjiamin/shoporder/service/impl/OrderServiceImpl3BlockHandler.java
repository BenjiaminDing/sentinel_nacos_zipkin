package cn.com.benjiamin.shoporder.service.impl;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * created at 2021/9/15 16:37  sentinel_nacos
 */
@Slf4j
public class OrderServiceImpl3BlockHandler {

    /**blockHandler = "blockHandler1"指定的方法  下面  要求
     * 1 当前方法的返回值和参数要跟原方法一致
     * 2 但是允许参数列表的最后加入一个BlockException 用来接收方法中发生的异常
     * @return
     */
    public  static String blockHandler1(String carNo, BlockException be) {



        // 自定义异常处理逻辑
        log.error("触发了blockHandler，内容::{}",be);
        return  " @SentinelResource学习{blockHandler1}，参数：："+carNo+"/"+be;
    }
}
