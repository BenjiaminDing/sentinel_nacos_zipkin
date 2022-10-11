package cn.com.benjiamin.shoporder.service.impl;


import cn.com.benjiamin.shopcommon.model.Order;
import cn.com.benjiamin.shoporder.dao.OrderDao;
import cn.com.benjiamin.shoporder.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Override
    public void save(Order order) {
        orderDao.insert(order);
    }
}
