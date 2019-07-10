**GIT使用说明！！！！**

注意，因为成都大学服务器的防火墙，造成gitlab获取不了正确的URL。

拉取项目请不要使用GITLAB给出的地址：http://946e1c66edaa/drp/drp.git

上面这个地址是错的！

应该使用这个URL: http://202.115.80.17:6166/newdrp/drp.git

并且注意，目前只支持HTTP方式。不支持SSH。

使用中如果遇到问题请问我，或者咨询杨中良，胡雄一。




**配置说明：**

1. drp.sql是建表脚本，请最先执行。
2. drp-sample-data.sql是一些测试数据，在第一步执行之后再执行。

数据库的连接参数使用的是环境变量的方式，这样配置可以保证不污染配置文件，配置方式如下：

**系统属性->高级->环境变量->在系统变量里面新建三个：**

```env
DRP_DB_SERVER
DRP_DB_USERNAME
DRP_DB_PASSWORD
DRP_FASTDFS_TRACKER
```

四个的值分别就是对应的数据库地址、用户名、密码、FASTDFS的tracker服务地址，类似下面的：

```env
xxxx:3306
root
password
192.168.3.166:22122
```

**部署说明：**
代码PUSH到GIT之后，成都大学的测试服务器会自动部署，但是如果有数据库改变（修改了drp.sql或者drp-sample-data.sql），则不会自动部署，需要手动去部署：

登录到下面的数据库管理地址：
```website
http://202.115.80.17:8166/mysql/

账户：root
密码：(密码请直接来问我)
```


登录进去之后对drp数据库进行更新操作。更新完之后再提交代码。


2018-04-27新变更：
把/drp/src/main/resources目录下的t_usr_info.sql文件放在navicat_premium中运行，表创建成功后再启动项目

2018-05-12新变更：
1.在这个地址下https://github.com/happyfish100/fastdfs-client-java  把fastdfs-client-java这个项目的压缩包下载下来
2.接着把fastdfs-client-java.zip解压到d盘（我是解压到的d盘）
3.打开cmd，进入到d盘下的fastdfs-client-java项目中，接着依次输入: mvn clean install、mvn package、mvn install，这样pom.xml文件中导入的
fastdfs-client-java包才能生效
（因为项目会用到fastdfs、nginx，而fastdfs、nginx又部署到了192.168.1.115这台机器上面，所以以后大家运行项目的时候，只要有功能涉及到nginx
和fastdfs的时候，都要连接5202这个wifi才能运行）


