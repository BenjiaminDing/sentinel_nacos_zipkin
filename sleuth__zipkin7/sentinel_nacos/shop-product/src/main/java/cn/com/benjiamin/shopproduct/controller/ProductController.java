package cn.com.benjiamin.shopproduct.controller;


import cn.com.benjiamin.shopcommon.model.Car;
import cn.com.benjiamin.shopcommon.model.Product;
import cn.com.benjiamin.shopproduct.service.ProductService;
import com.alibaba.nacos.shaded.com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@Slf4j
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/product/{pId}")
    public Product product(@PathVariable Integer pId) {
        Product product = productService.findProductById(pId);
        return product;
    }

    @PostMapping("/product")
    public Product product(@RequestBody Map<String,Object> param) {
        Product product = productService.findProductById((Integer) param.get("pId"));
        return product;
    }

    @PostMapping("/productVo")
    public Product productVo(@RequestBody Product prd) {
        Product product = productService.findProductById(prd.getPId());
        return product;
    }

    @PostMapping("/carVo")
    public String carVo(@RequestBody Car car) {
       log.info("res:::{}",car.toString());

        return car.toString()+"返回";
        // 入参
        // {
        //	"carNo": "苏8989",
        //	"address": "背景",
        //	"company": "西蒙兹",
        //	"eEngineNo": "s88f8f",
        //	"weight": 4
        //
        //}
        // 返回  eEngineNo问题
        // Car(carNo=苏8989, address=背景, company=西蒙兹, eEngineNo=null, weight=4)
    }

    /**
     *  网关限流  自定义API分组  测试
     *  // /product-serv/product/api1/**
     */
    @GetMapping("/product/api1/demo1")
    public String demo1() {
     return "api1--demo1";
            }
    @GetMapping("/product/api1/demo2")
    public String demo2() {
        return "api1--demo2";
    }

    @GetMapping("/product/api2/demo3")
    public String demo3() {
        return "api2--demo3";
    }

    @RequestMapping(value="/product/api2/demo4",method=RequestMethod.GET)
    public String demo4() {
        return "api2--demo4";
    }

}
