##交流社区

##资料
https://spring.io/guides/gs/serving-web-content/
https://v3.bootcss.com/components/#navbar-default

##过程bug

###未解决
出现java.net.SocketException: 

Connection reset 更改了httputil类，(可能是网络问题)，偶尔发生，待解决

查询回复时回复评论id和问题id重复，待解决（思考判断回复对象为问题还是评论）

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
（！！！！！！！待修改！！！！！！！）
```sql
CREATE DATABASE `community` /*!40100 DEFAULT CHARACTER SET utf8 */

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

create schema community collate utf8_general_ci;

create table comment
(
	id bigint auto_increment
		primary key,
	parentId bigint not null,
	type int not null,
	commentator int not null,
	gmtCreate bigint not null,
	gmtModified bigint not null,
	likeCount bigint default 0 null
);

create table question
(
	id int auto_increment
		primary key,
	title varchar(50) null,
	description text null,
	gmtCreate bigint null,
	gmtModified bigint null,
	creator int null,
	commentCount int default 0 null,
	viewCount int default 0 null,
	likeCount int default 0 null,
	tag varchar(256) null
);

create table user
(
	id int auto_increment
		primary key,
	accountId varchar(100) null,
	name varchar(50) null,
	token varchar(36) null,
	gmtCreate bigint null,
	gmtModified bigint null,
	bio varchar(256) null,
	avatarUrl varchar(256) null
);




```