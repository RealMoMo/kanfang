##kanfang_demo

仿QQ看房app---第七周学习Android路上

---
关于作者：
RealMoMo
> 关于我，欢迎关注  
   微信：[Real_Mo]()  
   邮箱：czb166@qq.com
-------------
####开发目的: 
<br>1.加深掌握android各组件</br>
<br>2.熟悉自定义view开发</br>
<br>3.了解一些开源g框架Retrofit2、Glide、PullToRefresh等等</br>
<br>4.学习抓包</br>
<br>5.界面美化</br>

###预览界面

<br> ![image](https://github.com/RealMoMo/kanfang/blob/master/project_picture/1.png)</br>
 <br>进入界面</br>
 
 <br> ![image](https://github.com/RealMoMo/kanfang/blob/master/project_picture/2.png)</br>
 <br> 首次进入app的引导界面</br>
  
  <br> ![image](https://github.com/RealMoMo/kanfang/blob/master/project_picture/3.png)</br>
  <br> 城市选择界面</br>
   
  <br>  ![image](https://github.com/RealMoMo/kanfang/blob/master/project_picture/4.png)</br>
   <br> 主界面</br>
    
   <br>  ![image](https://github.com/RealMoMo/kanfang/blob/master/project_picture/5.png)</br>
   <br>  其他界面等等</br>
     
   

###开发环境
Android Studio2.0


### 下载安装
导入模块，重新配置适合你开发环境build.gradle文件

```java  
  
apply plugin: 'com.android.application'

android {
    compileSdkVersion 23
    buildToolsVersion "23.0.3"

    defaultConfig {
        applicationId "momo.com.week7"
        minSdkVersion 15
        targetSdkVersion 23
        versionCode 1
        versionName "1.0"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:23.4.0'
    compile 'com.squareup.retrofit2:retrofit:2.0.2'
    compile 'com.squareup.retrofit2:converter-gson:2.0.2'
    compile 'com.squareup.retrofit2:converter-scalars:2.0.2'
    compile 'se.emilsjolander:stickylistheaders:2.7.0'
    compile 'com.github.bumptech.glide:glide:3.7.0'
    compile 'com.youth.banner:banner:1.4.4'
    compile 'com.belerweb:pinyin4j:2.5.1'
    compile 'in.srain.cube:ultra-ptr:1.0.11'
    compile 'cn.yipianfengye.android:zxing-library:2.1'
}

  
```

###Thanks
Everyone who has contributed code and reported issues and pull requests!



###TODO
<br>首页listview上拉加载更多数据的接口不适用，所以选择加载了加载相同的数据。</br>
<br>因消息、我的界面要与QQ服务端进行交互，没有实现功能。</br>

###Version
1.0----2016.11.26
