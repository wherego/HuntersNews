# HuntersNews
##一款基于Retrofit + RxJava + MVC架构开发的符合Google Material Desgin的新闻客户端（知乎日报+网易新闻+豆瓣图书）

![image](https://raw.githubusercontent.com/hunterliy10/HuntersNews/master/Library/src/main/res/drawable/screen1.jpg)
![image](https://raw.githubusercontent.com/hunterliy10/HuntersNews/master/Library/src/main/res/drawable/screen2.jpg)
![image](https://raw.githubusercontent.com/hunterliy10/HuntersNews/master/Library/src/main/res/drawable/screen3.jpg)
![image](https://raw.githubusercontent.com/hunterliy10/HuntersNews/master/Library/src/main/res/drawable/screen4.jpg)
![image](https://raw.githubusercontent.com/hunterliy10/HuntersNews/master/Library/src/main/res/drawable/screen5.jpg)

## Todo

- 该项目使用 MVC模式开发。该项目实现了基于 URL Scheme 的组件化，编译各组件时间相当于运行整体项目时间的 30%。
- 实现了 json 数据解析显示，利用 LruCache+DiskCache 和网络缓存实现了三级缓存， 减少了部分流量的使用。
- 通过实现主页面懒加载和布局优化减少了 10%的 App启动时间，配合修改 windowBackground 达到更快的启动速度。

## 第三方类库

- [Glide](https://github.com/bumptech/glide)
- [Okhttp](http://square.github.io/okhttp/)
- [RxJava](https://github.com/ReactiveX/RxJava)
- [Retrofit](https://square.github.io/retrofit/)

## About me

A hunter of Android.

Email: hunterliy@foxmail.com

## 开源协议

```
  Copyright YiyuanLiu

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
