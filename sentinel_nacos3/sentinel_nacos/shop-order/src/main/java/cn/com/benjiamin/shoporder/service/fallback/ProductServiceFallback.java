package cn.com.benjiamin.shoporder.service.fallback;

import cn.com.benjiamin.shopcommon.model.Product;
import cn.com.benjiamin.shoporder.feign.ProductService;
import org.springframework.stereotype.Component;

/**
 * created at 2021/9/15 16:37  sentinel_nacos
 * 这是一个容错类  需要实现Feign 所在的接口 ，并去实现接口中的所有方法
 * 一旦Feign远程调用出现问题了  就会进入当前类中同名方法，执行容错逻辑
 */
@Component
public class ProductServiceFallback  implements ProductService {
    @Override
    public Product findById(Integer pId) {
        Product product = new Product();

        product.setPId(-100);
        product.setPName("测试feign名字");
        return product;
    }
}
