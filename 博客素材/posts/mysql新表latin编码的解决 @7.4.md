PuTTY连接服务器发现了一颗隐藏多年的问题:无法输入中文,凸(艹皿艹 )
下面说一下在putty中建表格的时候不能输入中文报错的解决方法，一般会报下面的错误

>ERROR 1366 (HY000): Incorrect string value: '\xD6\xD0\xCE\xC4' for column

这时候我们只要修改我们想要插入中文字段的字符集编码就可以了

>alter table table_name modify username char(20) character set utf8;

编码问题是我永远的痛....
如果单单是特殊字符无法跨编码格式存储也好办,问题是转义字符表达方式把这个问题残忍的解决了!!!
导致我们根本不能从根源上发现问题蛤蛤蛤
对了说一下putty设置编码的方式:
![这里写图片描述](https://images0.cnblogs.com/blog/335252/201310/30100719-a398435d1e874b0e9477003c8486a135.jpg)
世界统一于utf8将是多么美好啊