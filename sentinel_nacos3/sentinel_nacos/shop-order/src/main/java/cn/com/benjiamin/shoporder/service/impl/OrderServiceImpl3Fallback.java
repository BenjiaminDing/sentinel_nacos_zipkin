package cn.com.benjiamin.shoporder.service.impl;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class OrderServiceImpl3Fallback {

    /**
     *参数要求 和 blockHandler1一致
     * @param carNo
     * @param te  Throwable  接收的所有异常 比  blockHandler1 大一些
     * @return
     */
    public static String fallback1(String carNo, Throwable te) {

        // 自定义异常处理逻辑
        log.error("触发了blockHandler，内容::{}",te);
        return  " @SentinelResource学习{fallback1}，参数：："+carNo+"/"+te;
    }
}
