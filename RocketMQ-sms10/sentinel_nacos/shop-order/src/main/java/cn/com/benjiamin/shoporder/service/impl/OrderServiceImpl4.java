package cn.com.benjiamin.shoporder.service.impl;

import cn.com.benjiamin.shopcommon.model.Order;
import cn.com.benjiamin.shopcommon.model.TxLog;
import cn.com.benjiamin.shoporder.dao.TxLogDao;
import cn.com.benjiamin.shoporder.service.OrderService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * created at 2021/9/15 16:37  sentinel_nacos
 */
@Service
@Slf4j
public class OrderServiceImpl4 {


    @Autowired
    private OrderService orderService;
    @Resource
    private TxLogDao txLogDao;
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    // 发送半事务消息
    public void createOrderBefore(Order order) {
        //4个参数，事务分组，主题，Message类型消息，参数
        String txLogId = UUID.randomUUID().toString();
        rocketMQTemplate.sendMessageInTransaction("tx_producter_group","tx_topic",
                MessageBuilder.withPayload(order).setHeader("txLogId",txLogId).build(),order);
    }

    //    public void createOrderBefore(Order order) {
//        String txId = UUID.randomUUID().toString();
////发送半事务消息
//        rocketMQTemplate.sendMessageInTransaction(
//                "tx_producer_group",
//                "tx_topic",
//                MessageBuilder.withPayload(order).setHeader("txId",
//                        txId).build(),
//                order
//        );
//    }
    //本地事物--注解
    @Transactional
    public void createOrder( String txLogId,Order order) {
// 本地事物代码
        orderService.save(order);
// 记录日志到数据库,回查使用
        TxLog txLog = new TxLog();
        txLog.setTxLogId(txLogId);
        txLog.setContent("事物测试");
        txLog.setDate(new Date());
        txLogDao.insert(txLog);
    }















}
