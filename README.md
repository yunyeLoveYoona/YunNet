# YunNet
一个微型的网络请求框架，类似于volley，不是重复造轮子，只为了解如何去造个轮子，还未完成..


提供了请求队列以及运行在UI线程中的请求回调，请求分为StringRequest和JsonRequest两种


使用方法很简单,只需获取RequestQueue，然后添加Request就可以了，可以多个请求使用一个监听，可以设置请求的优先级以及tag


Request提供了默认的http头信息的设置，当然你也可以自定义它们，Request提供了自定义的方法


RequestListenter提供了泛型支持，保证了在访问成功后你可以直接得到你想要的类型而不需要手动进行转换


想要停止请求队列时只需调用RequestQueue.stop()即可


![image](https://github.com/yunyeLoveYoona/YunNet/blob/master/app/src/main/res/drawable/a.png)
