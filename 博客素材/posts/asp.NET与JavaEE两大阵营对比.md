##1 各自的HelloWord版本
1.1 ASP
《%
 Response.Write("hello asp")
%》

文件名为test.asp。

1.2 ASP.net
《%@ page Language="C#" %》
《%
 Response.Write("hello asp.net");
%》

文件名为test.aspx。

需要注意的是由于ASP.net支持多种语言开发，而默认的语言是VB.net，这里使用的是C#语言，所以必须对其进行明确控制。否则编译时会按照VB.net进行，从而报错！

1.3 JSP
《%
    out.println("hello jsp");
%》

文件名为 test.jsp

1.4 分析
asp,aspx,jsp都采用了相同的嵌入语法《%代码%》，都内置了用于输出文本的对象，ASP-Response，ASP.net-Response, JSP-out。

对于语言来讲，VBScript不区分大小写，而C#和Java都区分；

对于命名习惯，VBScript和C#的类、属性、方法首字母都大写，而Java除了类其它的统统都习惯以小写字母开头。

##2 基本语法
2.1 嵌入表达式
（1）ASP

《%= %》

（2）ASP.net

《%= %》

（3）JSP

《%= %》

可见，三者完全相同。

2.2 嵌入声明
（1）ASP

《script Language="VBScript" runat="server》

 sub f
      Response.Write("hello asp from script")
 end sub

《/script》

（2）ASP.net

《script Language="C#" runat="server"》

void f()

{

    Response.Write("asp.net from script");

}

《/script》

（3）JSP

《%!

   public void f()

  {

       out.println("jsp ");

  }

%》

可见，ASP和ASP.net使用的语法相同，而JSP不一样。

2.4 嵌入程序片段
（1）ASP

《%程序片段%》

（2）ASP.net

《%程序片段%》

（3）JSP

《%程序片段%》

可见三者的语法完全一样。

##3 内置对象
3.1 ASP
（1）Request对象

代表了客户端发送到服务器端的信息。携带了如用户端浏览器信息、请求字符串、表单数据、cookies等。

（2）Response对象

代表了服务器端返回到客户端的相关内容与操作，可以通过它设置返回内容，返回cookie设置，是否缓存等。

（3）Application对象

代表了整个应用，是应用范围内的变量。多个页面共享它，所以会涉及到并发访问的问题，所以提供了Lock和Unlock方法进行同步处理。

（4）Session对象

代表了一个会话，会话有一个唯一的ID标识，客户端一般通过cookie或者请求字符串来提供会话的标识。

（5）Server对象

代表了服务器端的环境，提供了注册组件、映射路径等基本操作。

（6）ObjectContext对象

用于控制ASP的事务处理，编程时很少直接用到。

3.2 ASP.net
（1）Request对象

作用与ASP中的Request相同。

（2）Response对象

作用与ASP中的Request相同。

（3）Application对象

作用与ASP中的Application相同。

（4）Session对象

作用与ASP中的Session相同。

（5）Server对象

作用与ASP中的Session相同。

（6）Page对象

代表当前的页面，或者编译后的类。

3.3 JSP
（1）request对象

作用与ASP，ASP.net中的Request相同，JSP里是小写。

（2）response对象

作用与ASP,ASP.net中的Response类似，JSP里是小写。

（3）application对象

作用与ASP,ASP.net中的Application相同，JSP里是小写。

（4）session对象

作用与ASP,ASP.net中的Session相同，JSP里是小写。

（5）out对象

实现向客户端的输出，作用类似ASP,ASP.net中的Response.Write()。

（6）page对象

代表当前页面，或者编译后的Servlet。

##4 数据库访问使用的对象
4.1 ASP
ASP采用了ADO技术访问数据库。

（1）Connection类

代表了一个数据库管理系统的连接。

Set conn = Server.CreateObject("ADODB.Connection")

（2）Command类

对连接好的数据库执行命令。

（3）RecordSet类

记录集。

Set rs = Server.CreateObject("ADODB.Recordset")

（4）Field类

（5）Parameter类

（6）Property类

（7）Error类

4.2 ASP.net
ASP.net采用ADO.net技术访问数据库。

（1）SqlConnection

与ASP中的Connection一样。

（2）SqlCommand

与ASP中的Command一样。

（3）DataSet

与ASP中的RecordSet类似。

4.3 JSP
JSP采用了JDBC技术来访问数据库。

（1）Connection类

负责数据库的 连接。

（2）Statemaent类

类似于ASP中的Command，负责SQL语句的执行。

（3）ResultSet

结果集，类似于ASP的RecordSet，以及ASP.net的DataSet。

##5 结论
通过分析比较可以看出ASP,ASP.net,JSP有着非常多的共同点，都是对Http以及SQL数据库处理的规范化与流程化，其设计思路完全一致，只是技术实现存在细节差异。

尽管各自的开发环境、编码规范等等也存在不小的差异，但是由于其设计理念和处理流程极其类似，所以一个项目一开始往往只有一个版本，一旦后来发展的 比较好，就会陆续推出其他平台的版本，比如discuz最早就是PHP开发的，后来有了ASP.net的版本。同样一个程序员如果真正掌握了任何一种平 台，其开发经验90%都可以用到其他平台上，很多程序员在ASP.net和JSP之间转换只需要2周时间，这并不奇怪。