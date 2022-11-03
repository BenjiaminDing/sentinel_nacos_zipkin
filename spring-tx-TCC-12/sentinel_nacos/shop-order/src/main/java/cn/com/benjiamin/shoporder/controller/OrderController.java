package cn.com.benjiamin.shoporder.controller;


import cn.com.benjiamin.shopcommon.model.Order;
import cn.com.benjiamin.shopcommon.model.Product;
import cn.com.benjiamin.shoporder.feign.ProductService;
import cn.com.benjiamin.shoporder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

//@RestController
public class OrderController {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private OrderService orderService;
    @Resource
    private DiscoveryClient discoveryClient;
/* 方式一
    @GetMapping("/order/prod/{pId}")
    public Order order(@PathVariable("pId") Integer pId) {
        Product product = restTemplate.getForObject("http://localhost:8081/product/" + pId, Product.class);
        if (product == null) {
            return null;
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
        return order;
    }
*/

    // 方式二
//    @GetMapping("/order/prod/{pId}")
//    public Order order(@PathVariable("pId") Integer pId) {
//        List<ServiceInstance> instances=discoveryClient.getInstances("service-product");
//        // index是指有几个实例的数量
//        int index=new Random().nextInt(instances.size());
//        // 下面是从集合中拿出一个实例
//        ServiceInstance  serviceInstance=instances.get(index);
//
//        String url=serviceInstance.getHost()+":"+serviceInstance.getPort();
//
//
//        Product product = restTemplate.getForObject("http://"+url+"/product/"+pId,Product.class);
////        Product product = restTemplate.getForObject("http://service-product/product/"+pId,Product.class);
//
//        if (product == null) {
//            return null;
//        }
//        Order order = new Order();
//        order.setUId(1);
//        order.setUsername("测试用户");
//
//        order.setPId(product.getPId());
//        order.setPName(product.getPName());
//        order.setPPrice(product.getPPrice());
//        order.setNumber(1);
//        order.setStock(1);
//        orderService.save(order);
//        return order;
//    }


    @Resource
    private ProductService productService;

    @GetMapping("/order/prod/{pId}")
    public Order order(@PathVariable("pId") Integer pId) {

        Product product = productService.findById(pId);


        if (product == null) {
            return null;
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
        return order;
    }
}
