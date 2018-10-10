由于JavaScript的单线程特征,很多函数标准库中没有提供,于是我们只能超越标准库啦,实现一个sleep()函数就是其一:
```
function sleep(d){
  for(var t = Date.now();Date.now() - t <= d;);
}
 
sleep(5000); //当前方法暂停5秒
```
当然了这个方法不适合长期使用