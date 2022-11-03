package cn.com.benjiamin.shoporder;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.Resource;

@SpringBootTest
@EnableDiscoveryClient
class ShopOrderApplicationTests {

    /**
     * 用于rocketmq消息类型的测试使用，
     * 普通消息：
     * 01可靠同步发送、
     * 02可靠异步发送和
     * 03单向发送
     */
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    //同步消息
    @Test
    public void testSyncSend() {
//参数一: topic， 如果想添加tag 可以使用"topic:tag"的写法
//参数二: 消息内容
//参数二: 超时时间
        SendResult sendResult =
                rocketMQTemplate.syncSend("test-topic-1:tag_ben", "这是一条同步消息",10000);
        System.out.println(sendResult);
        //SendResult [sendStatus=SEND_OK, msgId=AC1484055F1018B4AAC27372EEF80000, offsetMsgId=C0A85E8000002A9F00000000001BE89C, messageQueue=MessageQueue [topic=test-topic-1, brokerName=bennode2, queueId=2], queueOffset=0]
    }

    //异步消息
    @Test
    public void testAsyncSend() throws InterruptedException {

//参数一: topic, 如果想添加tag 可以使用"topic:tag"的写法
//参数二: 消息内容
//参数三: 回调函数, 处理返回结果
        rocketMQTemplate.asyncSend("test-topic-1", "这是一条异步消息",
                new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println("回调成功");
                        System.out.println(sendResult);
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        System.out.println(throwable);
                    }
                });
//让线程不要终止
        System.out.println("===========================");
        Thread.sleep(30000);
    }


    //单向消息
    @Test
    public void testOneWay() {
        rocketMQTemplate.sendOneWay("test-topic-1", "这是一条单向消息");
    }

    //同步顺序消息[异步顺序 单向顺序写法类似]
    @Test
    public void testSyncSendOrderly() {
//第三个参数用于队列的选择
        for (int i = 0; i <5; i++) {
            rocketMQTemplate.syncSendOrderly("test-topic-1", "这是一条异步顺序消息",
                    "xx");
        }

    }


}


