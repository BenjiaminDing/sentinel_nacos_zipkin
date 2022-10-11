package cn.com.benjiamin.shoporder.controller;


import cn.com.benjiamin.shopcommon.model.Order;
import cn.com.benjiamin.shopcommon.model.Product;
import cn.com.benjiamin.shoporder.feign.ProductService;
import cn.com.benjiamin.shoporder.service.OrderService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@RestController
public class OrderController2 {





    // 方式四  使用feign
    @Resource
    private ProductService productService;
    @Resource
    private OrderService orderService;

    @GetMapping("/order/prod/{pId}")
    public Order order(@PathVariable("pId") Integer pId) {

         /*
          使用feign方式远程调用
          */
        Product product = productService.findById(pId);


        if (product == null) {
            return null;
        }

//  模拟线程堵塞，等待，tomcat默认最大线程200个
        try {
            Thread.sleep(20001);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Order order = new Order();
        order.setUId(1);
        order.setUsername("测试用户");

        order.setPId(product.getPId());
        order.setPName(product.getPName());
        order.setPPrice(product.getPPrice());
        order.setNumber(1);
        order.setStock(1);
        orderService.save(order);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return order;
    }


//todey is 2022/5/24/23:27

}

