java_hw
=======

java 一万行

一：
数据库字符集的设置：
SET character_set_client = utf8 ;
SET character_set_connection = utf8 ;
SET character_set_database = utf8 ;
SET character_set_filesystem = utf8 ;
SET character_set_results = utf8 ;
SET character_set_server = utf8 ;

[mysql]
default-character-set=utf8
[mysqld]
character-set-server=utf8
建表的时候也注意字符集
二：
service.xml字符集设置
Connector节点 URIEncoding="UTF-8"；
response.setContentType("text/html;charset=UTF-8");

三：数据库连接包
把mysql-connector-java-5.1.7-bin.jar拷贝到tomcat下lib目录就可以了。

四：
把参考包放到相应的lib下