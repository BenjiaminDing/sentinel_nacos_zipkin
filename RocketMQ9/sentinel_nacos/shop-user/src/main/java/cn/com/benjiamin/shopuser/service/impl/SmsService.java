package cn.com.benjiamin.shopuser.service.impl;

import cn.com.benjiamin.shopcommon.model.Order;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * created     by benjiamin at 2022-10-22 / 22:51 /51
 */
@Slf4j
@Service
/**
 * 注解的使用
 *
 ConsumeMode consumeMode() default ConsumeMode.CONCURRENTLY;

 MessageModel messageModel() default MessageModel.CLUSTERING;

 RocketMQ支持两种消息模式:
 广播消费: 每个消费者实例都会收到消息,也就是一条消息可以被每个消费者实例处理；
 集群消费: 一条消息只能被一个消费者实例消费
 */
@RocketMQMessageListener(
        consumerGroup = "shop-user",
        topic = "order-topic",
        consumeMode=ConsumeMode.ORDERLY,  //有序ORDERLY--无序CONCURRENTLY
        messageModel = MessageModel.CLUSTERING  //广播模式BROADCASTING--集群模式CLUSTERING
)
public class SmsService implements RocketMQListener<Order> {
    @Override
    public void onMessage(Order order) {
        log.info("benjiamin--收到一个订单信息{},接下来发送短信", JSON.toJSONString(order));
    }
}

