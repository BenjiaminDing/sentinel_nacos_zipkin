package cn.com.benjiamin.shopproduct.controller;


import cn.com.benjiamin.shopcommon.model.Product;
import cn.com.benjiamin.shopproduct.service.ProductService;
import cn.com.benjiamin.shopproduct.service.impl.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class ProductController {

    @Resource
    private ProductService productService;

    @Resource
    private ProductServiceImpl productServiceImpl;


    @GetMapping("/product/{pId}")
    public Product product(@PathVariable("pId") Integer pId) {
        Product product = productService.findProductById(pId);
        return product;
    }
//    @PostMapping("/productVo/{pId}")
//    public Product products(@PathVariable("pId") Integer pId) {
//        Product product = productService.findProductById(pId);
//        return product;
//    }



    @GetMapping("/product/spring-tx")
    public void testSpringtx() {
//        productServiceImpl.modifyTwoFormDB();
        productServiceImpl.transModifyProduct2();

    }



}
