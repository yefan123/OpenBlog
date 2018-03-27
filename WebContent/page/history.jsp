<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=yes">

    <title>历史 | OpenIdea</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <link type="text/css" rel="stylesheet" href="/Blog/css/public.css"/>
    <link type="text/css" rel="stylesheet" href="/Blog/css/history.css"/>

</head>
<body>


<div class="head_line"></div>

<div class="container" id="main">

    <div id="header"></div>

    <div class="row c_center">
        <div class="col-md-3" id="left_content">

            <div id="title">
                <h2>
                    <a href="/Blog/index.html">OpenIdea</a>
                </h2>
                <h5 class="text-muted">WALKING THROUGH — <span id="versions_num">${versions_num}</span> — HISTORY
                    VERSIONS</h5>
            </div>

            <div class="c_center" id="person_info">
                <h1>Version @23.1</h1>
                <h5 class="text-muted">Thx for Ur time !</h5>
                <br>
            </div>

            <div id="list">
                <table class="table table-hover c_center">
                    <tr>
                        <td><a href="/Blog/index.html"><span class="glyphicon glyphicon-home"></span>
                            &nbsp;&nbsp;首 页</a></td>
                    </tr>
                    <tr>
                        <td><a href="/Blog/MainServlet?device=phone"><span class="glyphicon glyphicon-film"></span>
                            &nbsp;&nbsp;文 章</a></td>
                    </tr>
                    <tr>
                        <td><a href="/Blog/TagsServlet?id=0"><span class="glyphicon glyphicon-tags"></span>
                            &nbsp;&nbsp;标 签</a></td>
                    </tr>
                    <tr>
                        <td class="active"><a href="#"><span class="glyphicon glyphicon-book"></span>
                            &nbsp;&nbsp;历 史</a></td>
                    </tr>
                    <tr>
                        <td><a href="page/about.html"><span class="glyphicon glyphicon-user"></span>
                            &nbsp;&nbsp;关 于</a></td>
                    </tr>
                </table>
            </div>
            <br/>
        </div>
        <div class="col-md-1" id="center_content"></div>

        <div class="col-md-8 " id="axis_div">


            <%--中间内容由ajax生成--%>


        </div>

    </div>
    <br>
    <div class="foot_line"></div>
</div>
<br>
<div id="footer">
    <a href="/Blog/index.html">OpenIdea</a> | Powered by <a href="http://www.oracle.com/">JavaEE</a>
</div>
<br><br><br>
<!-- 回到顶部 -->
<a href="#" class="box">
    <div class="top_arrow glyphicon glyphicon-menu-up"></div>
    <div class="top_str">TOP</div>
</a>

<%--js放下面,加快渲染,同时节省window.onload--%>
<script src="./js/history.js"></script>


</body>
</html>