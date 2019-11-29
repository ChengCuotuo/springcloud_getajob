# springcloud_getajob

SpringCloud 学习的记录 ：https://github.com/ChengCuotuo/springclouddemo

学习的笔记可以在 CSDN 找到我的博客：https://blog.csdn.net/cheng_cuo_tuo/article/details/103209057，博客的里面资料很少，因为是照着 哔哩哔哩 的 SpringCloud 学习视频照着做的，不敢班门弄斧，想要详细了解的还是自己刷一遍视频吧。



这个项目是一个练习，把我之前做的一个例子进行了微服务的拆分，使用的版本如下：

``` xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.0.1.RELEASE</version>
    <relativePath/>
</parent>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-dependencies</artifactId>
    <version>Finchley.RC1</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>
```

目录介绍：

> 前端： frontadmin，使用的是 Vue
>
> 后端：由于本人计算机处理器性能不是很好，项目的 Module 达到一定数量之后，就无法识别 Module 的目录结构，一些配置也无法生效，所以写在连个项目中（只要配置好，其实可以随便拆）
>
> > getajob:  包含公共的 api、eureka、gatway、service、consume
> >
> > getajobmonitor: 包含 ConfigServer、dashboard
>
> 配置文件：getajob-config



贴出一张 dashboard 监控的照片

![dashboard](https://github.com/ChengCuotuo/springcloud_getajob/blob/master/dashboard.png)

服务只拆出了两个部分，一个 service-b 处理登录，注册，日志查询，service-a处理基本信息的操作

安全部分...... Spring Security 在拆分之后不好用就砍掉了，接下来可以好好研究一下
还有 gatway 部分实现了功能，没有给它投入使用。
