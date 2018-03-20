-- MySQL dump 10.13  Distrib 5.6.39, for Linux (x86_64)
--
-- Host: localhost    Database: myblog
-- ------------------------------------------------------
-- Server version	5.6.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `myblog`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `myblog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `myblog`;

--
-- Table structure for table `label_relation`
--

DROP TABLE IF EXISTS `label_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `label_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagId` int(11) DEFAULT NULL,
  `postId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `labels`
--

DROP TABLE IF EXISTS `labels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `labels` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT '',
  `number` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `t_article`
--

DROP TABLE IF EXISTS `t_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(80) NOT NULL,
  `time` datetime DEFAULT '2017-09-18 00:00:00',
  `star` int(11) DEFAULT '0',
  `comment` int(11) DEFAULT '0',
  `visit` int(11) DEFAULT '0',
  `content` text,
  `length` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `t_comment`
--

DROP TABLE IF EXISTS `t_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) DEFAULT NULL,
  `nickname` varchar(30) DEFAULT NULL,
  `content` text,
  `time` datetime DEFAULT '1970-01-01 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment`
--

LOCK TABLES `t_comment` WRITE;
/*!40000 ALTER TABLE `t_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_history`
--

DROP TABLE IF EXISTS `t_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(10) CHARACTER SET latin1 DEFAULT NULL,
  `version` varchar(10) CHARACTER SET latin1 DEFAULT NULL,
  `content` varchar(60) DEFAULT NULL,
  `isBig` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_history`
--

LOCK TABLES `t_history` WRITE;
/*!40000 ALTER TABLE `t_history` DISABLE KEYS */;
INSERT INTO `t_history` VALUES (30,'2017-09-02','0.0','博客诞生',0),(31,'2017-09-02','0.0','测试阶段:阿里云vps成功内测前端模板',0),(32,'2017-09-09','0.0','测试阶段:服务器重定向:彻底重写后端:立场从asp.NET转向JavaEE',1),(33,'2017-09-10','0.0','测试阶段:完成基于session-cookie的访客记录机制',0),(34,'2017-09-10','0.0','测试阶段:dao层和service层书写完毕,投入使用',0),(35,'2017-09-17','0.9','测试阶段:数据库连接池从dbcp迁移至c3p0,完善高并发mysql访问机制',1),(36,'2017-09-19','1.0','博客正式于上海服务器上线!',1),(37,'2018-01-02','1.1','解决了url中文乱码问题',0),(38,'2018-01-02','1.2','博客迁移至本人名下,补全开发者信息',0),(39,'2018-01-05','2.2','首页div框架左右交换,可读性更强,手机端显示文章更方便',1),(40,'2018-01-06','2.5','增加鼠标事件,big模式下动态显示\'阅读全文\'',0),(41,'2018-01-07','2.7','完善\'关于\'板块,增加作者坐标等信息',0),(42,'2018-01-15','3.7','网站主色调大变革,增加半透明蒙版,扁平化设计更鲜明!',1),(43,'2018-01-20','3.8','美化了一些细节;更新了壁纸和部分文字对齐等',0),(44,'2018-01-21','3.9','依国家法律,完善了首页页脚的备案信息,本博客成为正式的ICP(互联网内容提供商)',0),(45,'2018-01-22','4.0','修复了一些bug,修改部分字体样式',0),(46,'2018-01-23','4.2','容器更新:解决cookie中键入空格的问题',0),(47,'2018-01-23','5.2','优化了文章列表显示算法,避免了全表扫描,即将推出分页机制,提高访问速度',1),(48,'2018-01-24','5.4','增加了支付宝打赏功能(｡･ω･｡)嘻嘻',0),(49,'2018-01-24','5.5','优化了一些细节;改良了评论机制',0),(50,'2018-01-24','5.7','优化了排名机制,第三次避免了全表扫描',0),(51,'2018-01-24','5.9','做了一些前端美工的活儿;修改了部分RGB',0),(52,'2018-01-25','6.9','时间轴板块大改革,更名为history板块,记录网站所有历史版本更新信息!!',1),(53,'2018-01-25','7.0','一怒之下重写了一遍history.css,增强了新版块在移动端上的体验~',0),(54,'2018-01-25','7.1','修改了iptables映射,隐藏了8080端口:缩短url长度!',0),(55,'2018-01-25','7.2','修改了虚拟路径,覆盖了tomcat首页:再次缩短url长度,博客唯一域名:openidea.xin',0),(56,'2018-01-25','7.4','优化了数据库',0),(57,'2018-01-26','7.5','剔除了不必要的骨干div,使得手机端显示时main区撑满整个屏幕宽度',0),(58,'2018-01-26','7.6','优化了标签机制,修复了列表泛型,避免了NullPointerException',0),(59,'2018-01-26','8.0','管理员界面嵌入了一个占网站源码2/3的Markdown编辑器,对于我来说当然是重大更新啦~不过放心,这不影响网站的健壮性',1),(60,'2018-01-26','8.1','改良了文章编辑机制,从此小编更新文章更频啦',0),(61,'2018-01-26','9.1','意外发现admin的重大安全漏洞,吓得我赶紧down掉服务器重写了一遍过滤器,很抱歉网站挂掉了2个小时T^T',1),(62,'2018-01-26','9.2','修复了admin后台;修复了一些空指向的href',0),(63,'2018-01-27','9.7','巩固了后台mvc架构',0),(64,'2018-01-27','9.8','修复了一些bug',0),(65,'2018-01-27','10.0','添加了浏览器缓存,提高了服务器响应效率',0),(66,'2018-01-28','10.1','半重大更新:删除了service层些许不必要的方法,提高了LoginServlet的响应速度!',0),(67,'2018-01-28','10.2','整理了public.css,使得前端层次化更分明,更精简',0),(68,'2018-01-28','11.2','文章列表引入分页机制,同时完善了ajax的json交互',1),(69,'2018-01-29','11.5','在新推出的分页机制上做了一系列美化,引入了CSS3新技术之transform,支持多个主流浏览器:)',0),(70,'2018-01-29','12.3','去掉了很鸡肋的文章作者信息,drop掉了整整一个列!因为博客的作者都是我一个人啊哈哈(￣▽￣)／❤',1),(71,'2018-01-29','12.5','彻底删除了测试包和一些出入补丁,减轻了源码体积,网站从此进入健壮、成熟发展的新阶段!!感谢各位!',1),(72,'2018-01-30','12.6','做了一些安全加固',0),(73,'2018-02-01','12.6','筹划了新的标签机制和缓存方案',0),(74,'2018-02-02','12.9','删除了分类机制,统一由标签机制管理,减轻容器负担',0),(75,'2018-02-02','13.2','半重大更新:部署并发布了新设计的label系统雏形(将会公开到GitHub)',0),(76,'2018-02-02','13.3','强化了新开发的labels管理系统',0),(77,'2018-02-02','13.4','做了一点前端优化,统一了浮动替代表格',0),(78,'2018-02-03','13.5','修复了一些bug',0),(79,'2018-02-04','15.0','有史以来最重大更新:完成了健壮了标签管理系统,翻新了整个数据库结构,并更新了所有文章٩(๑❛ᴗ❛๑)۶',1),(80,'2018-02-04','15.2','全新推出\"热门标签\"栏目于首页,取代列出全部标签',0),(81,'2018-02-04','15.3','优化了一下响应式的布局',0),(82,'2018-02-05','15.5','做了些小修小补比如格式化了时间戳;优化了一下算法比如null掉了model层一些多余的成员',0),(83,'2018-02-05','15.6','新增了几个用户友好的前端控件,自寻探索哦~',0),(84,'2018-02-05','15.8','统计了每一篇博文的字数,并显示在相关位置',0),(85,'2018-02-05','15.9','删除了几乎所有JSP注释,解放了带宽,此时整个源代码即趋于完美!',0),(86,'2018-02-06','16.0','优化了管理员接口,距网站开源还有一步之遥!',0),(87,'2018-02-07','16.1','CSS大整改:删除了所有重复的层叠规则,整合到public.css,同时进行了格式压缩,再一次大幅度解放带宽的压力',0),(88,'2018-02-07','16.2','压缩并更新了favicon.ico,提升了品牌的价值(＾＿－)',0),(89,'2018-02-07','16.4','进行了大面积的图片压缩,也是网站上线以来第5次裁剪行动,此时网站根目录体积逼近10M',0),(90,'2018-02-07','17.4','drop掉mysql自动备份表,从此进入手动备份时代',1),(91,'2018-02-08','18.4','服务器更新:CentOS下每天23:59重启tomcat,以便进行一些垃圾清理和缓存刷新',1),(92,'2018-02-08','18.6','扔掉一个过滤器;修复一些bug',0),(93,'2018-02-09','18.9','容器优化:解决mysql重连问题,提高可用性',0),(94,'2018-02-18','19.0','优化~',0),(95,'2018-02-20','20.1','确定了全局缓存模型:将大多request属性存入application中,大大加强了对DDoS的防御力',1),(96,'2018-02-21','20.3','春节结束前最后一次优化',0),(97,'2018-02-23','20.4','MySQL微整:去除了多余的select项目',0),(98,'2018-02-25','20.7','全面改良了评论机制及其界面,删了多余的ajax验证包',0);
/*!40000 ALTER TABLE `t_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'primary_key',
  `user_name` varchar(20) NOT NULL COMMENT 'username',
  `user_password` varchar(20) NOT NULL COMMENT 'password',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `t_visitor`
--

DROP TABLE IF EXISTS `t_visitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_visitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(50) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `web_ip` varchar(50) DEFAULT NULL,
  `host` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=235 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-25 16:42:15
