package cn.com.benjiamin.shoporder.controller;


import cn.com.benjiamin.shopcommon.model.Order;
import cn.com.benjiamin.shopcommon.model.Product;
import cn.com.benjiamin.shoporder.feign.ProductService;
import cn.com.benjiamin.shoporder.service.impl.OrderServiceImpl4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderControllerMQTrans4 {

    @Resource
    private ProductService productService;

    @Autowired
    private OrderServiceImpl4 orderServiceImpl4;

    @GetMapping("/order/prod_mqTrans/{pId}")
    public Order orderMQTrans(@PathVariable("pId") Integer pId) {

         /*
          使用feign方式远程调用
          */
        Product product = productService.findById(pId);


        if (product == null) {
            return null;
        }

//  模拟线程堵塞，等待，tomcat默认最大线程200个


        Order order = new Order();
        order.setUId(1);
        order.setUsername("测试用户");

        order.setPId(product.getPId());
        order.setPName(product.getPName());
        order.setPPrice(product.getPPrice());
        order.setNumber(1);
        order.setStock(1);

        /**
         * rocketMQ--事务消息---分布式事务--使用测试
         */
        orderServiceImpl4.createOrderBefore(order);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return order;
    }



}

