关于spring的学习笔记和记录：

pom.xml ：该文件是用于管理所有的包，是MAVEN之下的文件。管
理所有的依赖情况。

<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.7.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
</parent>
这一段代码可以帮助管理下载最新的jre包。

.gitignore 该文件是用来选择在git时提交的文件情况，那些提交那些不提交

test文件夹之下管理所有的test文件
main文件夹内
java文件夹保存controller service等
resource static保存静态文件 template保存模板等文件
application.property保存路由配置服务配置等内容，定义配置等

Gradle:


网址构成：
https://localhost:8888//hello?name=andrew

hello为下属路由 将键为name 数值为andrew的参数传入服务器


可以用来装入超连接{}

关于GIT：
之前学过学的不仔细，都忘的差不多了，从新尝试在IDE中试用一下
git系列

SpringBoot devtool 可以实现热部署

Mybatis Generator：MyBatis Generator是用来生成相应的mapper.xml文件、model对象和mapper文件。
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
用该方法实现mapper的自动注入

异常处理：@ExceptionHandler 和 @ControllerAdvice来处理Controller错误
@Error处理404等错误


Jquery：利用ajax不刷新页面提交 异步刷新
```
$.ajax({
  type: 'POST',
  url: url,
  data: data,
  success: success,
  dataType: dataType
});
`````
localStorage：长久保存

SessionStorage：关闭tab后删除

