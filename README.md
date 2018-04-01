# OpenIdea Blog - 开源灵感博客

a personal blog site based on Java/Mysql - 基于Java/Mysql的个人博客网站;此时已经更新至version@23.1;这是一个java web博客项目，尽最大可能实现mvc模式，没有使用到框架，实现了首页预览，文章发布，点赞，评论，Markdown格式编写，分类，标签，阅读排行，时间轴，管理员管理博客，访客记录等。

## introduction - 简介


* 博客用到的技术有:h5/css3/es6,jsp,servlet,mysql,bootstrap,ajax,json

* 其中jsp技术用到了jstl标签库,el表达式和标准动作等,并没用使用纯java,便于日后扩展

* 数据库连接池使用了[c3p0](http://www.mchange.com/projects/c3p0/) 具体参数详见配置文件

* 后端也没有任何框架,摒弃了庞大而臃肿的SSL框架(洁癖哈哈)

* Markdown编辑器使用了开源的[editor.md](https://github.com/pandao/editor.md)


## 软件使用说明*

	1. 先确保服务器环境的正确安装和配置,过程略,其中包括jdk1.8+;tomcat8.5+;mysql5.7+;
	
	2. 源码拿到手,先导入eclipse或者intellij进行编译;
	
	3. 然后找到src目录下的c3p0-config.xml文件填写唯一的mysql用户名和密码;(必须拥有读和写的权限)
	
	4. 后台进入mysql创建名为'myblog'的库,然后导入src目录下的openidea.sql文件;
	
	5. 进入myblog.t_user表,按照表头提示添加一个管理员(站长)账户;
	
	6. 将导出的war包上传到tomcat服务器的webapps目录下,即可通过'yourdomain:8080/Blog'访问;
	
	7. 管理员后台登录默认是根目录下的myLogin.html,最好修改文件名,以使其只为自己服务;
	
	8. 尽情的享受修改源码的快乐吧!

### database - 数据库

* posts - 文章表
* comment - 评论表
* user - 管理员表
* visitor - 访问记录表
* history - 版本更新表
* labels - 标签表
* relation - 标签&文章关系表

### directory intro - 目录介绍

#### java
* blog/admin 管理员相关的的servlet
* blog/dao 数据库接口类
* blog/daoImple 数据库接口实现类
* blog/db 非主要业务的DB操作/连接池的实现
* blog/filter 过滤器
* blog/model bean包
* blog/service 面向web的服务层
* blog/servlet 主要的控制器servlet
* blog/utils 辅助工具包

#### 前端目录
* admin 管理员网页
* css CSS目录
* editormd MD编辑器
* error 错误页面
* img 图片
* js JS目录
* page 主要网页
* upload 图片上传文件夹

### jar intro - 使用到的jar包
* mysql-connector-java   jdbc包
* c3p0-0.9.5.2.jar     c3p0数据库连接池
* mchange-commons-java.jar 		c3p0依赖包
* commons-beanutils-1.9.3-bin.zip  JavaBean工具包
* commons-logging-1.2-bin.zip
* jstl.jar  		jstl标签库
* standard.jar      标准包
* commons-fileupload.jar 文件上传
* commons-io-2.5.jar    io操作
* commons-lang-2.5.jar      语言包
* commons-collections-3.1 集合工具包
* json-lib-2.1-jdk15 json包
* ezmorph-1.0.3		json辅助包

### demo - 演示
>原作者的成品网站示例:

[www.openidea.xin](http://www.openidea.xin)

