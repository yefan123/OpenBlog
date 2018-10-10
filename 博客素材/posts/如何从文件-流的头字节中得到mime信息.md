####抛出一个疑问:OS是如何识别并按照一定编码打开纯文本文件的?
在写网络爬虫的时候，需要根据链接来获取文件类型，将内容正确存储。之前我都是根据链接的后缀来判断的，比如：

>http://avatar.csdn.net/5/5/E/3_github_38885296.jpg

这个链接指向的文件就是个jpg文件。但是后来发现有诸如

http://jprice.360buyimg.com/getSkuPriceImgService.action?skuId=1850001109&origin=1&webSite=1&type=1
这样的链接，这招就不灵了。后来谷歌百度了一下也没发现解决办法。后来机缘巧合在Java Network Programming上找到了一个办法：

URLConnection class provides two static methods to help programs figure out the MIME type of some data; you can use these if the content type just isn't available or if you have reason to believe that the content type you're given isn't correct。

就是说URLConnection提供了两种方法可以猜测（根据实测结果，这个猜测是相当的准）数据的MIME类型。

  第一个是：

public static String guessContentTypeFromName(String name)

这个方法根据URL文件部分的后缀名来判断类型，跟之前我的方法一样。这个不能解决上面那个问题。
第二个是：
public static String guessContentTypeFromStream(InputStream in)
这个方法是根据流的前面几个字节来判断类型，这个就不需要文件后缀名了，完全可以解决上面那个问题。

测试代码如下：
```java
BufferedInputStream
 bis = null;
HttpURLConnection
 urlconnection = null;
URL
 url = null;        
        url
 = new URL(strUrl);
    urlconnection
 = (HttpURLConnection) url.openConnection();
    urlconnection.connect();
bis
 = new BufferedInputStream(urlconnection.getInputStream());
    System.out.println("file
 type:"+HttpURLConnection.guessContentTypeFromStream(bis));
```

常见的文件头字节对照表如下:
> jpg: 255,216
gif: 71,73
bmp: 66,77
png: 137,80
doc: 208,207
docx: 80,75
xls: 208,207
xlsx: 80,75
js: 239,187
swf: 67,87
mp3: 73,68
wma: 48,38
mid: 77,84
rar: 82,97
zip: 80,75
xml: 60,63

###总结:
#####头字节(特殊类型在中间的特定位置)也叫魔数!
#####魔数还没有形成一个标准,并不需要申请,因为是为自家软件解析文件服务的.
文件头字节信息是一种通用标准,行业默契,可以方便不通的系统交换理解不通的数据,除了媒体文件,文件头还用于区分文本文件的编码信息,注意,编码信息目前并不是存放在文件系统中被OS识别和ftp传递的,虽然我们都很希望实现这一点,但是文件头的规范轻松地解决了这个问题.