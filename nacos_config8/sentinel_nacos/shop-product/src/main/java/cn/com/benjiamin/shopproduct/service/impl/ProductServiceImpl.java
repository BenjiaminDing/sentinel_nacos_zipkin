package cn.com.benjiamin.shopproduct.service.impl;

import cn.com.benjiamin.shopcommon.model.Product;
import cn.com.benjiamin.shopproduct.dao.ProductDao;
import cn.com.benjiamin.shopproduct.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class ProductServiceImpl implements ProductService {


    @Resource
    private ProductDao productMapper;

    @Override
    public Product findProductById(Integer pId) {

        Product product = productMapper.selectById(pId);

        return product;
    }


}
