>转自百度百科...

所谓favicon，即Favorites Icon的缩写，顾名思义，便是其可以让浏览器的收藏夹中除显示相应的标题外，还以图标的方式区别不同的网站。当然，这不是Favicon的全部，根据浏览器的不同，Favicon显示也有所区别：在大多数主流浏览器如FireFox和Internet Explorer (5.5及以上版本)中，favicon不仅在收藏夹中显示，还会同时出现在地址栏上，这时用户可以拖曳favicon到桌面以建立到网站的快捷方式；除此之外，标签式浏览器甚至还有不少扩展的功能，如FireFox甚至支持动画格式的favicon等。

- 中文名 网站头像 
- 外文名 Favorites Icon 
- 英文简写 favicon
####如何调用
浏览器调用favicon的原理是首先在网页所在目录寻找favicon.ico文件，如果没有找到就去网站的根目录寻找。所以最简单的方法就是将制作好的favicon文件命名为favicon.ico然后上传到网站的根目录下。
如果您需要将Favicon.ico放到其他目录下，或者希望让不同的网页显示不同的Favicon，就需要在网页Html文件中做设定了，具体设置也很简单，在Html中的< head>部分加入如下的代码：
< link rel=”icon” href=”/dir/favicon.ico” mce_href=”/dir/favicon.ico” type=”image/x-icon”>
< link rel=”shortcut icon” href=”/dir/favicon.ico” mce_href=”/dir/favicon.ico” type=”image/x-icon”>
< link rel="icon" href="animated_favicon1.gif" type="image/gif" >
含义：在浏览器标签中显示favicon，在收藏夹中显示favicon。
