# shawnlibrary
这是一个轻量级的 Android 开发框架基于MVC架构实现
本框架中封装使用了[BRVAH](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)

RecyclerView 采用原生组件使用更加方便强大。
##增加
工具部分增加了 [utilcode](https://github.com/liangfhNoDev/AndroidUtilCode) 里面集成收集了很多必须常用的代码片段使用很方便。

数据库方面使用了sugar

##使用
###Gradle
### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```Java
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

### Step 2. Add the dependency

```Java
dependencies {
		compile 'com.github.liuxiong1115:shawnlibrary:v1.0.0'
}
```

###maven
```Java
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```  
```Java
  <dependency>
	    <groupId>com.github.liuxiong1115</groupId>
	    <artifactId>shawnlibrary</artifactId>
	    <version>v1.0.0</version>
	</dependency>
```  

### step3

拷贝`conf.gradle`到您的项目根目录，并修改项目gradle文件下引入：
```groovy
apply from: "conf.gradle"
```


4.修改BaseConf配置类，主要针对log、cache、router、imageloader。若采用默认配置，此步骤可略过.


## 重要说明

* [ButterKnife](https://github.com/JakeWharton/butterknife)使用的是8.4.0版本。请前往官网查看使用方法，否则ButterKnife注入视图将失败。
* [EventBus](https://github.com/greenrobot/EventBus)使用的是3.0.0版本,在onEvent方法上使用了注解`@Subscribe`，具体如何使用可以查看官网。




