##交流社区

##资料
https://spring.io/guides/gs/serving-web-content/
https://v3.bootcss.com/components/#navbar-default

##过程bug
okhttp 使用4.x.x 会出现出错误：
KotlinReflectionNotSupportedError: Kotlin reflection implementation is not found at runtime

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