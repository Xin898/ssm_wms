### SpringBoot 解决的问题

#### 起步依赖:

起步依赖本质上是一个Maven项目对象模型(Project Object Model，POM)，定义了对其他库的传递依
赖，这些东西加在一起即支持某项功能。
简单的说，起步依赖就是将具备某种功能的依赖坐标打包到一起，并提供一些默认的功能。

#### 自动配置:

springBoot的自动配置，指的是springboot，会自动将一些配置类的bean注册进ioc容器，我们可以需要的地方使用@autowired或者@resource等注解来使用它。
“自动”的表现形式就是我们只需要引我们想用功能的包，相关的配置我们完全不用管，springboot会自动注入这些配置bean，我们直接使用这些bean即可
springboot: 简单、快速、方便地搭建项目；对主流开发框架的无配置集成；极大提高了开发、部署效率

使用spring initializer的方法构建Spring项目

本质上说，Spring Initializr是一个Web应用，它提供了一个基本的项目结构，能够帮助我们快速构建一个基础的Spring Boot项目

![image-20220123161249620](D:\JavaWorkspace\springboot_quickstart\images\createSpring.png)



#### SpringBoot Test类：

  注意test类的包需要和项目包保持一致

![image-20220124000138920](C:\Users\morro\AppData\Roaming\Typora\typora-user-images\image-20220124000138920.png)

#### spring热启动

#### spring全局配置文件以及格式：

- application.properties
- application.yaml
- application.yml

### SpingBoot核心功能解析

#### SpringBoot整合Redis

添加Redis的依赖包

配置Redis数据库连接

编写Redis的工作类

#### SpringBoot整合MyBatis

##### 注解方式

- 使用@select注解编写数据库操作接口

```java
public interface CommentMapper {

    @Select("SELECT * FROM t_comment WHERE id = #{id}")
    Comment findById(Integer id);

}
```

- 在SpringBoot启动文件中开启扫描

```java
@SpringBootApplication
@MapperScan("com.lagou.mapper") //执行扫描mapper的包名
public class SbIntegrationDemo01Application {

    public static void main(String[] args) {

        SpringApplication.run(SbIntegrationDemo01Application.class, args);
    }
}
```

##### 配置文件方式

##### SpringBoot 支持的视图技术

Thymeleaf的配置方法：

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" media="all"
href="../../css/gtvg.css" th:href="@{/css/gtvg.css}" />
<title>Title</title>
</head>
<body>
<p th:text="${hello}">欢迎进入Thymeleaf的学习</p>
</body>
</html>
```



### SpingBoot 仓库项目开发需求：

基本需求：

1. 子账户建立，一个子账户对应一个子仓库；--->子仓库下两张表
2. 支付接口API；
3. 货物出库操作，有操作记录(数量和时间)；---出库表
4. 货物入库操作，有操作记录(数量和时间)；---入库表
5. 上平编码编辑

## 功能

* 系统操作权限管理。系统提供基本的登入登出功能，同时系统包含两个角色：系统超级管理员和普通管理员，超级管理员具有最高的操作权限，而普通管理员仅具有最基本的操作权限，而且仅能操作自己被指派的仓库。
* 请求URL鉴权。对于系统使用者登陆后进行操作发送请求的URL，后台会根据当前用户的角色判断是否拥有请求该URL的权限。
* 基础数据信息管理。对包括：货物信息、供应商信息、客户信息、仓库信息在内的基础数据信息进行管理，提供的操作有：添加、删除、修改、条件查询、导出为Excel和到从Excel导入。
* 仓库管理员管理。对仓库管理员信息CRUD操作，或者为指定的仓库管理员指派所管理的仓库。上述中的仓库管理员可以以普通管理员身份登陆到系统。
* 库存信息管理。对库存信息的CRUD操作，导入导出操作，同时查询的时候可以根据仓库以及商品ID等信息进行多条件查询。
* 基本仓库事务操作。执行货物的入库与出库操作。
* 系统登陆日志查询。超级管理员可以查询某一用户在特定时间段内的系统登陆日志。
* 系统操作日志查询。超级管理员可以查询某一用户在特定时间段内对系统进行操作的操作记录。、
* 密码修改。

