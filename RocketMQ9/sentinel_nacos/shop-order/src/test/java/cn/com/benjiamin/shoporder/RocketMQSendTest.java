package cn.com.benjiamin.shoporder;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * created     by benjiamin at 2022-10-22 / 16:39 /39
 */
public class RocketMQSendTest {



    public static void main(String[] args) throws Exception {
//        1. 创建消息生产者, 指定生产者所属的组名
        DefaultMQProducer   defaultMQProducer=new DefaultMQProducer("myproducer-group");
//        2. 指定Nameserver地址--也就是rocket部署环境的ip--我们这里使用的是虚拟机地址
        defaultMQProducer.setNamesrvAddr("192.168.94.128:9876");
//        3. 启动生产者
        defaultMQProducer.start();
//        4. 创建消息对象，指定主题、标签和消息体
        Message  message=new Message("myTopic", "myTag",("RocketMQ Message-ben").getBytes());
//        5. 发送消息 --消息内容--超时时间
     SendResult   result= defaultMQProducer.send(message,10000);
        System.out.println(result);
//        6. 关闭生产者
        defaultMQProducer.shutdown();


        /**
         * 如果发送一直失败可以尝试开发端口9876--但是还是失败
         * 然后尝试--可以尝试关闭虚拟机的centos7的防火墙--命令{systemctl stop firewalld}
         *  然后就可以成功--打印日志
         *  SendResult [sendStatus=SEND_OK, msgId=AC1484053F0018B4AAC26FCF22A40000, offsetMsgId=C0A85E8000002A9F000000000018C450, messageQueue=MessageQueue [topic=myTopic, brokerName=bennode2, queueId=2], queueOffset=0]
         */
    }
}
