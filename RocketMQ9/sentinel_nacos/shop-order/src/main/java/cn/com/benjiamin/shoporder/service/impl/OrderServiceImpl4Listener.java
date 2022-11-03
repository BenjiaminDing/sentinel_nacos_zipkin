package cn.com.benjiamin.shoporder.service.impl;

import cn.com.benjiamin.shopcommon.model.Order;
import cn.com.benjiamin.shopcommon.model.TxLog;
import cn.com.benjiamin.shoporder.dao.TxLogDao;
import cn.com.benjiamin.shoporder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * created at 2021/9/15 16:37  sentinel_nacos
 */
@Service
@Slf4j
@RocketMQTransactionListener(txProducerGroup="tx_producter_group")
public class OrderServiceImpl4Listener implements RocketMQLocalTransactionListener {

    @Autowired
    private OrderServiceImpl4 orderServiceImpl4;
    @Resource
    private TxLogDao txLogDao;

    //执行本地事务
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object arg) {
      try{

          Order orderArg=(Order)arg;
        String txLogId=  (String) message.getHeaders().get("txLogId");
          orderServiceImpl4.createOrder(txLogId, orderArg); //第三步


          return RocketMQLocalTransactionState.COMMIT;// 第四步
      } catch (Exception e) {
          return RocketMQLocalTransactionState.ROLLBACK;

                  }
    }


    // 第五步骤--消息回查
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {

        //查询日志记录
        String txLogId=  (String) message.getHeaders().get("txLogId");

        TxLog txLog = txLogDao.selectById(txLogId);  // 第六步
        System.out.println("txLog::>>>>"+txLog.toString());
        if (txLog == null) {
            return RocketMQLocalTransactionState.COMMIT;  // 第七步根据事务状态 commit
        } else {
            return RocketMQLocalTransactionState.ROLLBACK;// 第七步根据事务状态 commit
        }
    }






}
