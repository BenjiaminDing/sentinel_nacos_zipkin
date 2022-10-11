# 工程简介

说明：
首先说明失败的搭配方式；
01--jpa方式操作数据库一直提示dao对象没有注入spring容器所以一直无法找到bean对象
02--使用mybaits方式操作数据库，如果使用nacos1.3.2也是无法正常使用，目前原因不知道

最后指定配合方式
nacos使用2.0.4版本--存放配置信息数据选用mysql8--数据库信息在3308端口
操作数据库是用mybatis，
此外shop数据库使用mysql5版本-shop购物信息数据存放3306

user-server-8071
product-server-8081




# 延伸阅读

