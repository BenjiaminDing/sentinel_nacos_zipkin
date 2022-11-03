package cn.com.benjiamin.shoporder.controller;

import cn.com.benjiamin.shopcommon.model.Order;
import cn.com.benjiamin.shopcommon.model.Product;
import cn.com.benjiamin.shoporder.feign.ProductService;
import cn.com.benjiamin.shoporder.service.OrderService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * created     by benjiamin at 2022-10-22 / 22:15 /15
 */

@Slf4j
@RestController
public class OrderController4 {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    //  是spring封装的一个类
    @Resource
    private RocketMQTemplate   rocketMQTemplate;

    /**
     *
     * @param pid
     * @return
     */
    @GetMapping("/order/prodmq/{pid}")
    public Order ordermq(@PathVariable("pid") Integer pid) {
        log.info(">>客户下单,这时候要调用商品微服务查询商品信息");
//通过fegin调用商品微服务
        Product product = productService.findById(pid);
        if (product == null){
            Order order = new Order();
            order.setPName("message--下单失败");
            return order;
        }
        log.info(">>商品信息,查询结果:" + JSON.toJSONString(product));
        Order order = new Order();
        order.setUId(1);
        order.setUsername("测试用户");
        order.setPId(product.getPId());
        order.setPName(product.getPName());
        order.setPPrice(product.getPPrice());
        order.setNumber(1);
        orderService.save(order);
//下单成功之后,将消息放到mq中
        rocketMQTemplate.convertAndSend("order-topic", order);
        return order;
    }
}
