package cn.com.benjiamin.shoporder.service.fallback;

import cn.com.benjiamin.shopcommon.model.Product;
import cn.com.benjiamin.shoporder.feign.ProductService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * created at 2021/9/15 16:37  sentinel_nacos
 * 这是容错类，他要求我们使用
 */
@Slf4j
@Component
public class ProductServiceFallbackFactory  implements FallbackFactory<ProductService> {
 //  throwable就是feign在调用过程中产生的异常
    @Override
    public ProductService create(Throwable throwable) {
        return new ProductService(){

            @Override
            public Product findById(Integer pId) {
                log.error("{}",">>>>>>>throwable");
                log.error("{}",throwable);
                throwable.printStackTrace();

                Product product = new Product();

                product.setPId(-100);
                product.setPName("商品微服务调用出现异常，已经进入到了容错方法中");
                return product;
            }
        };
    }
}
