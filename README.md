##交流社区

##资料
https://spring.io/guides/gs/serving-web-content/
https://v3.bootcss.com/components/#navbar-default

##过程bug

###未解决
出现java.net.SocketException: Connection reset 不抛出异常暂时解决 

具体原因未知 判断1：可能重复使用方法没有关闭连接导致异常抛出

更改了httputil类，暂时解决（观望）

###已解决

okhttp 使用4.x.x 会出现出错误：

KotlinReflectionNotSupportedError: 
Kotlin reflection implementation is not found at runtime

okhttp的证书验证错误和连接超时错误：

通过使用HttpUtils方法getUnsafeOkHttpClient()解决证书

以及设置连接时长
OkHttpClient client = getUnsafeOkHttpClient()
.newBuilder()
.connectTimeout(50000, TimeUnit.MILLISECONDS)
.readTimeout(50000, TimeUnit.MILLISECONDS)
.build();
解决


##工具
SpringBoot2.2.5

OkHttp3.14.1

Lombok

Mybatis

Thymeleaf

##脚本
```sql
CREATE DATABASE `community` /*!40100 DEFAULT CHARACTER SET utf8 */

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `token` varchar(36) DEFAULT NULL,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `bio` varchar(256) DEFAULT NULL,
  `avatar_url` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8



create table question
(
	id int auto_increment,
	title varchar(50) null,
	description TEXT null,
	gmt_create bigint null,
	gmt_modified bigint null,
	creator int null,
	comment_count int default 0 null,
	view_count int default 0 null,
	like_count int default 0 null,
	tag varchar(256) null,
	constraint question_pk
		primary key (id)
);




```