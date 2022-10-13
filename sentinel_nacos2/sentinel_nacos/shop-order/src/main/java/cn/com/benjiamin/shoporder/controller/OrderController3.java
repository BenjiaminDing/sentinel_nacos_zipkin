package cn.com.benjiamin.shoporder.controller;


import cn.com.benjiamin.shoporder.service.impl.OrderServiceImpl3;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * sentinel使用测试
 */
@RestController
@Slf4j
public class OrderController3 {

    @Resource
    private OrderServiceImpl3 orderServiceImpl3;

    int i = 0;

    @GetMapping("/order/message1")
    public String message1() {
        i++;
        if (i % 3 == 0) {
            throw new RuntimeException("0.333大于阈值0.25");
        }
        log.info("结果：：{}", i);
        log.info("{},{}", i, 8);
        return "服务容错,messge1" + "/" + orderServiceImpl3.messageService();
    }


    @GetMapping("/order/message2")
    public String message2() {

        i++;
        if (i % 3 == 0) {
            throw new RuntimeException("0.333大于阈值0.25");
        }

        return "服务容错，message2" + "+/" + orderServiceImpl3.messageService();
    }


    /**
     * 学习  4.6.3 热点规则  处理
     * @param name
     * @param age
     * @return
     */
    @GetMapping("/order/message3")
    @SentinelResource("hot-message3")   // 热点规则需要这个
    public String message3(String name, int age) {


        return "服务容错，降级规则，热点规则message3" + "/" + name + "," + String.valueOf(age);
    }



    @GetMapping("/order/message4")
    public String message4() {

        return "学习  4.6.4 授权规则  服务容错，授权规则，message4" + "/";
    }



}


/*

4.6.。。节后面的东西有点模糊，存在模糊点可以再回顾一下
 */
