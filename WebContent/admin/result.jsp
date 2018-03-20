<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>发布结果</title>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">


    <!-- 仅仅引入页面公共样式 -->
    <link type="text/css" rel="stylesheet" href="/Blog/css/public.css"/>

    <!-- 计时器:5s返回文章 -->
    <script type="text/javascript">
        let time = 6;
        let timer;

        function checkTimer() {
            time--;
            if (time <= 0) {
                timer.parentNode.click();
            }
            timer.innerHTML = time;
        }
    </script>

</head>
<body style="background:#eee">


<div class="head_line"></div>
<br><br>
<div class="container" id="main">
    <div class="row c_center" style="margin:0, auto;">

        <% if (request.getParameter("type").equals("post")) { %>

        <% //如果非管理员访问则被打回首页
            if (request.getSession().getAttribute("user") == null) {
                request.getRequestDispatcher("/Blog/index.html").forward(request, response);
            }
        %>

        <c:choose>
            <c:when test="${article==null}">
                <h1 class="glyphicon glyphicon-remove" style="color:gray"></h1>
                <h4>不知什么原因导致创建或更新文章失败了.....</h4>
            </c:when>
            <c:otherwise>
                <h1 class="glyphicon glyphicon-ok" style="color:lime"></h1>
                <h4>成功!是否查看新文章:</h4>
                <h3><a href="/Blog/ArticleServlet?id=${article.id}">《${article.title}》</a></h3>
            </c:otherwise>
        </c:choose>

        <% } else if (request.getParameter("type").equals("comment")) { %>

        <c:choose>
            <c:when test="${comment==null}">
                <h1 class="glyphicon glyphicon-remove" style="color:gray"></h1>
                <h4>错误:${comment_info}</h4>
                <h3><a href="/Blog/ArticleServlet?id=${postId}">返回文章 (<span id="timer"></span>)</a></h3>
            </c:when>
            <c:otherwise>
                <h1 class="glyphicon glyphicon-ok" style="color:lime"></h1>
                <h4>评论成功!</h4>
                <h3><a href="/Blog/ArticleServlet?id=${postId}">返回文章 (<span id="timer"></span>)</a></h3>
            </c:otherwise>
        </c:choose>

        <!-- 启动读秒计时器 -->
        <script type="text/javascript">
            window.onload = function () {
                timer = document.querySelector('#timer');
                setInterval(checkTimer, 1000);
            }
        </script>

        <% } else { } %>

    </div>
    <br><br>
    <div class="foot_line"></div>
</div>


<div id="footer">
    <a href="/Blog/index.html">OpenIdea</a>
</div>
</body>
</html>