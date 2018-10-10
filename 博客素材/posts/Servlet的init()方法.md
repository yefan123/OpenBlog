今天稍微学习了一下 Servlet，Servlet 主要有以下几个方法： 
1) destroy() 
2) getServletConfig() 
3) getServletInfo() 
4) init(ServletConfig arg0) 
5) service(ServletRequest arg0, ServletResponse arg1)

destroy 和 service 方法的调用时刻很明显，关键这个 init 方法的调用时刻不是很清楚，遂经过百度和一番实验后得出了结论。

init 方法的执行时刻其实与 servlet 的配置有关，可以看到以下代码的load-on-startup结点，如果结点的值大于等于 0，则在 Servlet 实例化的时候执行，间隔时间由具体的值决定，值越大，则越迟执行。如果小于 0 或者没有配置，则在第一次请求的时候才同步执行 ， 注意 init 方法只执行一次

>servlet>
    servlet-name>helloServlet/servlet-name>
    servlet-class>javaweb.HelloServlet/servlet-class>
    load-on-startup>1/load-on-startup>
/servlet>
servlet-mapping>
    serlvet-name>helloServlet/servlet-name>
    url-pattern>/hello/url-pattern>
/servlet-mapping>

----
##总结
init 方法是随 Servlet 实例化而被调用的，因为 load-on-startup 就是用来设置 Servlet 实例化时间的。

因此，init 方法执行的时刻有两种：

（1） load-on-startup 的值大于等于0，则伴随 Servlet 实例化后执行。

（2） load-on-startup 的值小于0 或者 不配置， 则在第一次 Servlet 请求的时候执行。

---
######init方法经常用于模型层(model层)的初始化,将数据库中的原始数据生成一个个javabean,并且存入application对象中