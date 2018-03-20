<%--
  Created by IntelliJ IDEA.
  User: jim001
  Date: 2018/3/14
  Time: 上午8:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${title}</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link type="text/css" rel="stylesheet" href="./css/public.css"/>
    <link type="text/css" rel="stylesheet" href="./css/editor.css"/>

    <link rel="stylesheet" href="./editormd/css/style.css"/>
    <link rel="stylesheet" href="./editormd/css/editormd.css"/>

    <script src="./editormd/js/zepto.min.js"></script>
    <script src="./editormd/js/editormd.js"></script>

    <script src="./js/editor.js"></script>
</head>
<body>
<%
    //如果非管理员访问则被打回首页
    if (request.getSession().getAttribute("user") == null) {
        request.getRequestDispatcher("/index.html").forward(request, response);
    }
%>


<div class="head_line"></div>
<div class="container" id="main">
    <div id="title"><h2><a href="/Blog/index.jsp">OpenIdea</a></h2>
    </div>

    <form action="/Blog/UpdatePostServlet?option=${option}" method="post">

        <div class="info">
            <!-- old_id -->
            <span class="help">old_id</span>
            <input type="text" class="form-control" name="old_id" value="${edit_article.id}" readonly="readonly">
            <!-- new_id -->
            <span class="help">new_id (修改要慎重)</span>
            <input type="text" class="form-control" name="new_id" value="${edit_article.id}">
            <!-- star -->
            <span class="help">star</span>
            <input type="text" class="form-control" name="star" value="${edit_article.star}">
            <!-- comment -->
            <span class="help">comment</span>
            <input type="text" class="form-control" name="comment" value="${edit_article.comment}" readonly="readonly">
            <!-- visit -->
            <span class="help">visit</span>
            <input type="text" class="form-control" name="visit" value="${edit_article.visit}">
            <!-- title -->
            <span class="help">标题</span>
            <input type="text" class="form-control" name="title" value="${edit_article.title}">
            <!-- time -->
            <span class="help">时间</span>
            <input type="text" class="form-control" name="time" value="${edit_article.time}">


            <!-- tag -->
            <span class="help">标签</span><br/>
            <c:forEach var="tag" items="${all_tags_ranked}">
                <input class="btn btn-default" type="button" value="${tag.name}" onclick="tags_click(this,${tag.id})">&nbsp;
            </c:forEach>
            <input type="text" class="form-control" id="tags" name="tags" readonly="readonly">
        </div>


        <div class="foot_line"></div>
        <!-- content -->
        <div class="editormd" id="mdView">
            <textarea name="content">${edit_article.content}</textarea>
        </div>
        <br/>
        <input class="btn btn-success" type="submit" value="提交"/>
    </form>
    <br>
    <br>
    <br>
    <div class="foot_line"></div>
</div>

<div id="footer">
    <a href="/Blog/index.html">OpenIdea</a>
</div>
<br>
<br>

<script type="text/javascript">
    var testEditor;
    var jQuery = Zepto;
    $(function () {
        testEditor = editormd("mdView", {
            width: "90%",
            height: 720,
            path: './editormd/lib/',
            codeFold: true,
            searchReplace: true,
            saveHTMLToTextarea: true,    // 保存 HTML 到 Textarea
            htmlDecode: "style,script,iframe|on*", // 开启 HTML 标签解析，为了安全性，默认不开启
            emoji: true,
            taskList: true,
            tocm: true,
            tex: false,     //默认false
            flowChart: false,      //同上
            sequenceDiagram: false,     //同上
            imageUpload: true,
            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL: "/Blog/PicUploadServlet",
            //后台只需要返回一个 JSON 数据
            onload: function () {
                //console.log("onload =>", this, this.id, this.settings);
            }
        });
        testEditor.setToolbarAutoFixed(false);//工具栏自动固定定位的开启与禁用
    });
</script>

</body>
</html>
