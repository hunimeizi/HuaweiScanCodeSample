### 华为扫码demo

##### 权限申请
```js
ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ), 2
            )
```
```js
override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 2 && grantResults.size == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            //调用扫码接口，构建扫码能力，需您实现
            ScanUtil.startScan(this,REQUEST_CODE_SCAN_ONE,HmsScanAnalyzerOptions.Creator().create())
        } else {
            Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show()
        }
    }

```

##### demo功能
1.本Demo扫码识别和本地图片二维码识别
2.添加了生成二维码功能和保存二维码功能，注意添加一下manifest代码否则会保存图片错误
```js
android:requestLegacyExternalStorage="true"
```
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

#### 注意官方demo需要在build.gradle里面配置以下信息
```js
compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
```