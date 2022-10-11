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

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private OrderService orderService;
    @Resource
    private DiscoveryClient discoveryClient;
/* 方式一
    @GetMapping("/order/prod/{pId}")
    public Order order1(@PathVariable("pId") Integer pId) {
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
//    @PostMapping("/order/prod/{pId}")
//    public Order order2(@PathVariable("pId") Integer pId) {
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

    // 方式三 使用ribbon负载均衡      @LoadBalanced 生效
//    @GetMapping("/order/prod/{pId}")
//    public Order order3(@PathVariable("pId") Integer pId) {
//
//        Product product = restTemplate.getForObject("http://service-product/product/"+pId,Product.class);
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




    // 方式四  使用feign
    @Resource
    private ProductService productService;

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
//        orderService.save(order);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return order;
    }



    @GetMapping("/order/message")
        public String med() {
               return   "测试高并发";
            }
}
