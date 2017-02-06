# HuntersNews
##基于Rxjava+Retrofit开发的新闻客户端（知乎日报+网易新闻+豆瓣图书）
![image](https://raw.githubusercontent.com/hunterliy10/HuntersNews/master/Library/src/main/res/drawable/screen1.jpg)
![image](https://raw.githubusercontent.com/hunterliy10/HuntersNews/master/Library/src/main/res/drawable/screen2.jpg)
![image](https://raw.githubusercontent.com/hunterliy10/HuntersNews/master/Library/src/main/res/drawable/screen3.jpg)
![image](https://raw.githubusercontent.com/hunterliy10/HuntersNews/master/Library/src/main/res/drawable/screen4.jpg)
![image](https://raw.githubusercontent.com/hunterliy10/HuntersNews/master/Library/src/main/res/drawable/screen5.jpg)
###项目特点
- 该项目使用 MVC模式开发。该项目实现了基于 URL Scheme 的组件化，编译各组件时间相当于运行整体项
目时间的 30%。
- 实现了 json 数据解析显示，利用 LruCache+DiskCache 和网络缓存实现
了三级缓存， 减少了部分流量的使用。
- 通过实现主页面懒加载和布局优化减少了 10%的 App
启动时间，配合修改 windowBackground 达到更快的启动速度。

欢迎star、fork，后续还会持续改进...
