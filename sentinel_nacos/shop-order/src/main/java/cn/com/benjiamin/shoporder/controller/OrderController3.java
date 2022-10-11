package cn.com.benjiamin.shoporder.controller;




import cn.com.benjiamin.shoporder.service.impl.OrderServiceImpl3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController3 {

    @Resource
    private OrderServiceImpl3  orderServiceImpl3;

    int   i=0;
    @GetMapping("/order/message1")
        public String message1() {
//        orderServiceImpl3.message();
        log.info("77");
               return   "测试高并发messge1";
            }


    @GetMapping("/order/message2")
    public String message2() {
//        orderServiceImpl3.message();

//        i++;
//        if (i%3==0){
//            throw  new RuntimeException("jjjd");
//        }
log.info("888");
        return   "测试高并发message21";
    }
}
