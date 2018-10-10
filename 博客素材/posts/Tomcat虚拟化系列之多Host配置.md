    Tomcat创建多个虚拟主机，只需要修改server.xml，添加多个的Host，指定不同的name即可。

Tomcat的server.xml文件Host内容解析：
```

《Host name="localhost"  appBase="webapps"
            unpackWARs="true" autoDeploy="true"》
《!-- SingleSignOn valve, share authentication between web applications
             Documentation at: /docs/config/valve.html --》
 《!--
《Valve className="org.apache.catalina.authenticator.SingleSignOn" /》
        --》
《!-- Access log processes all example.
             Documentation at: /docs/config/valve.html
             Note: The pattern used is equivalent to using pattern="common" --》
《Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
               prefix="localhost_access_log." suffix=".txt"
               pattern="%h %l %u %t "%r" %s %b" /》
 《/Host》
在server.xml中使用《Host》标签包裹，其中Host标签的元素有：

     name  ： 指出这个虚拟主机的名字，可以使域名。

     appBase ： 如果要war文件自动部署的话指定这个目录，这个目录下的war文件会被自动部署。

    unpackWARs : 是否对appBase指定的目录下的war文件自动解压，true为自动解压，false为不自动解压，直接运行war文件。

    autoDeploy：设置是否在tomcat运行期间自动更新项目（包括在appBase添加新的项目，修改，删除，更新），true时，会自动更新appBase下的war项目，web.xml文件的更改，以及Host下指定《Context》 标签定制的项目（在appBase下的）。
```
（http://tomcat.apache.org/tomcat-7.0-doc/deployer-howto.html#Deploying_on_a_running_Tomcat_server）


在Host标签中，还可以添加子标签:
```
《Alias》域名《/Alias》

指定域名，可设置多个。支持正则.

《Context path=""  docBase="/www/a.war"  reloadable="true" /》

context标签是用来指定自定义的网站的url，以及映射实际的在系统的物理路径。

例如：《Context path="/abc"  docBase="/www/a.war"  reloadable="true" /》时，我们访问www.xxx.com/abc代替www.xxx.com/a。
```
    path  ：是要重命名后的路径，用/代表根路径，例如/abc

    docBase： 是指定war的真实的物理路径，可以不在appBase下。

    reloadable：为true时会自动更新，context指定的应用。


关于appBase和docBase路径的区别和关系：

    appBase：是可以自动部署war的路径，默认是在tomcat的安装路径下的webapps，如果                       用tomcat的默认的话使用相对路径，也可以使用绝对路径指定一个非tomcat默认的路径。

    docBase：与appBase没什么直接关系，它指出特定的应用的单独设置。如果war包在                            appbase下，可以使用相对路径，比如在appBase路径下有，a.war,设置                                docBase时可以用a来设置。常用使用绝对路径定义。


标签：Valve：

设置Host的访问日志

    classname ： 设置使用哪个类来处理tomcat的访问日志

    directory ： 设置log日志的存放路径，默认log是在tomcat的安装路径下logs目录下

    prefix ： 指定访问日志的前缀，也可以理解为名字。

    suffix ： 指定访问日志的后缀，可以理解为扩展名。

    pattern ： 为日志的记录格式。


###总结
虚拟主机,虚拟路径,以及虚拟端口号的映射是url映射的三大难点,只有合理配置这些映射关系,才能使你的网站更安全,更易于管理