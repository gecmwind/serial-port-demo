# 串口编程的环境搭建
本人开发环境，win10，jdk1.7 idea 2018，相关软件的安装请参考百度。。
# java监听串口有2种实现方式
## 1.1 sun公司提供的串口通信API：comm2.0.jar（Windows环境下）和comm3.0.jar（Linux/Solaris环境下）
> 经本人测试，sun公司提供的comm2.0.jar和相关的.dll文件只有win32环境的，不符合当前win64环境。linux环境下.so文件缺失
## 1.2 第三方apiR（XTX）
rxtx.qbang 官网：http://fizzed.com/oss/rxtx-for-java，根据自己电脑和服务器配置下载相关软件包。<br>

- linxu环境下也可直接使用`resources`目录下的环境配置
- windows环境前往rxtx官网下载配置即可。

例子提供了我开发时使用的配置，和服务器配置（resources文件）。
# 串口的模拟
目前大多数电脑可能都没有串口，我们可以使用串口模拟软件（VSPD），虚拟化串口进行开发调试。
百度下载就行，可以适用14天，应该够开发调试了……