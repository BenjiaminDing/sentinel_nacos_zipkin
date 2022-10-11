package cn.com.benjiamin.shopproduct.controller;


import cn.com.benjiamin.shopcommon.model.Product;
import cn.com.benjiamin.shopproduct.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/product/{pId}")
    public Product findProductById(@PathVariable("pId") Integer pId) {
        Product product = productService.findProductById(pId);
        return product;
    }
//    @PostMapping("/productVo/{pId}")
//    public Product products(@PathVariable("pId") Integer pId) {
//        Product product = productService.findProductById(pId);
//        return product;
//    }
}
