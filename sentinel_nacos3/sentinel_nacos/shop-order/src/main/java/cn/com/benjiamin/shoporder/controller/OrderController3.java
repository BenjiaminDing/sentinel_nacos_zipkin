package cn.com.benjiamin.shoporder.controller;


import cn.com.benjiamin.shopcommon.model.Order;
import cn.com.benjiamin.shopcommon.model.Product;
import cn.com.benjiamin.shoporder.feign.ProductService;
import cn.com.benjiamin.shoporder.service.OrderService;
import cn.com.benjiamin.shoporder.service.impl.OrderServiceImpl3;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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


    /**
     * 注解 @SentinelResource 注解学习
     *
     * @return
     */
    @GetMapping("/order/message/SentinelResource")
    public String SentinelResource() {
        String carNo = "苏A0900D";

        return orderServiceImpl3.SentinelResourceService(carNo);
    }

    /**
     * sentinelResouse持久化 测试
     * @return
     */
    @GetMapping("/order/message/persistence")
    public String SentinelPersistence() {


        return "持久化，保存流控规则";
    }

    //----------------------------------------------------

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


        if (product.getPId()==-100) {
            Order order = new Order();
            order.setOId(-1009);
            order.setPName("下单失败");
            return order;
        }



        Order order = new Order();
        order.setUId(1);
        order.setUsername("测试用户");

        order.setPId(product.getPId());
        order.setPName(product.getPName());
        order.setPPrice(product.getPPrice());
        order.setNumber(1);
        order.setStock(1);
        // 创建订单
        orderService.save(order);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return order;
    }
}
