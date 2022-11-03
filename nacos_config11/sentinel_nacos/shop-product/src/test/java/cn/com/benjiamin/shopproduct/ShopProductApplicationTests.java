package cn.com.benjiamin.shopproduct;

import cn.com.benjiamin.shopcommon.model.Product;
import cn.com.benjiamin.shopproduct.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ShopProductApplicationTests {
@Resource
private ProductService  productService;
    @Test
    void findProduct() {
     Product  pp= productService.findProductById(1);
        System.out.println(pp);
    }

}
