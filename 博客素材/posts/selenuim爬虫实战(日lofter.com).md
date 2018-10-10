>LOFTER是网易公司2011年8月下旬推出的一款轻博客产品。
LOFTER专注于为用户提供简约、易用、有品质、重原创的博客工具、原创社区，以及有品质的手机博客应用。
LOFTER首次采用独立域名，口号为"专注兴趣，分享创作"。 一经上线，便受到了互联网众多文艺青年、摄影师、插画师的喜爱。

作为中国的Instagram,自然是很多人爬虫的目标,之前我写过一篇关于如何电脑批量上传Instagram图片的文章,这次来点高级的,来做一个lofter的批量下载器.

###环境
eclipse, selenium包, firefox
###目标
制作下载器,输入用户名,下载其账户上的所有照片
###原理介绍
"http://" + username + ".lofter.com/view"是lofter提供的用户图片摘要视图,可以通过ajax动态加载的方法在一张网页上显示出用户所有的图片.我们就可以通过selenium模拟用户滚动瀑布流,再通过ECMAScript捕捉到所有图片的url,最后利用java发送get包下载所有图片.
###准备工作
为了节省流量和时间,我们既不加载图片(加载出来的只是缩略图,没用),也不要加载界面了(节省本地资源).
注意,这两个节省是相互独立的.
>FirefoxOptions options = new FirefoxOptions();

		// 启动配置"不加载图片"
		options.addPreference("permissions.default.image", 2);

		// 启动参数"无界面"
		FirefoxBinary myBinary = new FirefoxBinary();
		myBinary.addCommandLineOptions("--headless");
		options.setBinary(myBinary);
>FirefoxDriver driver = new FirefoxDriver(options);

###实现方式
下一篇文章将公布源码

- 键入pageDown键以触发ajax瀑布流
- 通过Dom元素的element.children.length属性值的累加获得图片总数
- 在控制台输出所有url(暂停),以便核实再下载
- 开启多线程超时跳过机制,防止损坏的url
- 设置selenium的隐式等待和显式等待

###注意事项
在经过了多次试验之后得到的最优方案.

- 利用页面提供的数据(页首'文章数量'字段)可以得到用户全部的图片数量以供参考
- 循环检测的时候不要傻不拉几的数元素的数量,而要通过length值直接获得,因为JS比java要脆弱的多
- 结束标志最好基于数量增长周期超时
- url需要裁剪,去掉问号之后的参数(这些参数用来生成缩略图)
- 下载时切记从流的头字节获取mime信息,用来生成后缀名

>源码请见下文.