package cn.com.benjiamin.shoporder.feign;

import cn.com.benjiamin.shopcommon.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created at 2021/9/15 16:37  sentinel_nacos
 */
@FeignClient("service-product")
public interface ProductService {

    @RequestMapping("product/{pId}")
    public Product findById(@PathVariable("pId") Integer pId);

}
