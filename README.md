# IPCMessengerTest

IPC 方式之利用Messenger通信

Messenger的底层也是AIDL方式，它是封装给上层使用，由于它是一次处理一个请求，因此不用考虑线程同步的问题。

Messenger的优点是功能一般，支持一对多串行通信，支持实时通信。缺点是不能很好处理高并发情况，不支持RPC，数据通过Messenger进行传输，因此只能传输Bundle支持的数据类型。


Messenger的object字段在2.2之前不支持跨进程传输，2.2之后也仅仅能传输系统提供的Parcelable对象，意味着我们自己定义的Parcelable也无法通过object字段传输。

使用流程

1、创建service类

2、在xml文件中注册，并且指定其进程，实现不同进程间的通信

3、创建客户端。

详情请看代码


有一个坑，就是Android Studio 的Log打印中，下方的打印显示是区分进程的，在这个demo中，因为是进程间的通信，所以要点击倒三角切换进程查看相应的Log日志。
![sss](https://github.com/carrys17/Sucai/blob/master/1499011469(1).jpg)
