![rocketmq--内部结构](../../AppData/Roaming/Typora/typora-user-images/image-20221022232928147.png)

```

mqnamesrv--相当于注册中心
mqbroker--服务器
 cd /usr/local/rocketmq/rocketmq-all-4.8.0-bin-release/bin
 
./mqnamesrv    --第一个窗口

#让broker连接到注册中心namesrv上
./mqbroker -n localhost:9876   --第二个窗口


需要开两个窗口 没有使用后天启动
vim tools.sh   
添加 一行
export NAMESRV_ADDR=localhost:9876


#测试提供者和消费
./tools.sh org.apache.rocketmq.example.quickstart.Producer

./tools.sh org.apache.rocketmq.example.quickstart.Consumer


----------------
#下载控制台
https://github.com/apache/rocketmq-dashboard
下载rocketmq-dashboard

修改配置
    namesrvAddrs:
	  - 192.168.94.128:9876
	  - 192.168.94.128:9876
	  
#打成 jar包
	  
mvn clean package -Dmaven.test.skip=true  

#控制台访问 地址
192.168.94.128:7777
```

--

访问的地址192.168.94.128:7777 后图下

![image-20221022123229273](../../AppData/Roaming/Typora/typora-user-images/image-20221022123229273.png)

![image-20221022123453583](../../AppData/Roaming/Typora/typora-user-images/image-20221022123453583.png)



使用windows java程序生产消息

![image-20221022170958550](../../AppData/Roaming/Typora/typora-user-images/image-20221022170958550.png)



```
Nameserver中心的展示
```

![image-20221022170924839](../../AppData/Roaming/Typora/typora-user-images/image-20221022170924839.png)







```
首选是再linux-centos7创建一个rocketmq的 nameserver，启动nameerver,broker, 
再window这个使用idea 启动shop-order  创建消息推送到nameserver，由broker进行消息的发送，
使用shop-user接收mq推送的信息进行，触发sms短信的推送
```

