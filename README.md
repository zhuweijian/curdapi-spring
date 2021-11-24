零代码从数据库表自动生成CRUD接口。 对 （https://github.com/kolchagov/java-crud-api） 进行简单封装。

最近找了一些从数据库表直接生成接口的项目。比较热门的有 腾讯的apijson，https://github.com/Tencent/APIJSON。 试用一段时间，不太好用。
主要原因是 apijson太过庞大，集成了很多不必要的功能，内部原理比较复杂；再有就是 二次开发难度比较大；

https://github.com/kolchagov/java-crud-api CRUD功能都有，并且源码只有 6 7 个文件，二次开发难度低。
直接使用java-crud-api 源码运行也是可以的，因为 习惯了spring技术栈组件，因此对 java-crud-api做一个简单的springboot封装。

## 使用说明：
接口： /crud_api/{tableName}
tableName 指定表名，其余参数跟 java-crud-api 一样。 具体参考：https://github.com/kolchagov/java-crud-api

