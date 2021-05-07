### 华为扫码demo

##### 混淆
```js
-ignorewarnings
-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
-keep class com.huawei.hianalytics.**{*;}
-keep class com.huawei.updatesdk.**{*;}
-keep class com.huawei.hms.**{*;}
```

[集成华为统一扫码服务](https://developer.huawei.com/consumer/cn/forum/topic/0203318506342430030)
[官方文档](https://developer.huawei.com/consumer/cn/doc/development/HMSCore-Guides/service-introduction-0000001050041994)
[官方demo](https://gitee.com/hms-core/hms-scan-demo/tree/master)
#### 注意官方demo需要在build.gradle里面配置一下信息
```js
compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
```