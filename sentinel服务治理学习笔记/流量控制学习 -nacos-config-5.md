## nacos-config

```
是配置文件的扫描顺序
```

![image-20220604093950031](D:/音乐图片/typora图片/image-20220604093950031.png)

```
大致说明解释一下，
思路就是将在本地的配置参数发布到nacos-config中心里面，当启动项目的时候，可以自动读取配置文件的信息，例如数据库配置
使用http://127.0.0.1:8081/product/1做一个请求，如果可以查询到数据库的信息，说明，shop-product已经可以从配置中心拉去到application.peoperties文件信息
值得注意的是项目中的bootstrap.yml微服务名称决定了 配置中心中Data ID
```

![2](D:/音乐图片/typora图片/image-20220604110445610.png)

==配置动态刷新==

:fallen_leaf:

```
如果在配置中心修改，application。properties的内容，例如添加一个key-value值，
如何获取这个添加的数据，就需要 新增一个主动获取配置信息的类   注入ConfigurableApplicationContext，通过 applicationContext.getEnvironment().getProperty("key");的方式 ，获取value
下图是使用注解的方式
```

![image-20220604111827407](D:/音乐图片/typora图片/image-20220604111827407.png)
