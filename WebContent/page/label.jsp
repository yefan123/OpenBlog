<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=yes">

    <title>标签 | OpenIdea</title>


    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <link type="text/css" rel="stylesheet" href="/Blog/css/public.css"/>
    <link type="text/css" rel="stylesheet" href="/Blog/css/tags.css"/>

</head>
<body>

<!-- admin here -->
<c:if test="${sessionScope.user!=null}">
    <div id="admin">
        <c:choose>
            <c:when test="${id!=0}">
                <button class="btn btn-danger" onclick="delet_tag(${id})">
                    <span class="glyphicon glyphicon-trash">&nbsp;删除此标签</span>
                </button>
                <br>
            </c:when>
        </c:choose>
        <button class="btn btn-warning" onclick="refresh()">
            <span class="glyphicon glyphicon-refresh">&nbsp;刷新</span>
        </button>
        <br>
        <button type="button" class="btn btn-warning" onclick="logout()">
            <span class="glyphicon glyphicon-log-out">&nbsp;登出</span>
        </button>
        <div id="newTag">
            <input type="text">
            <br>
            <button class="btn btn-default">
                <span class="glyphicon glyphicon-plus" style="color:#5bc0de" onclick="add_tag()">&nbsp;添加&nbsp;</span>
            </button>
        </div>
    </div>
</c:if>

<div class="head_line"></div>

<div class="container" id="main">

    <div id="header"></div>

    <div class="row c_center">
        <div class="col-md-4" id="left_content">

            <div id="title">
                <h2>
                    <a href="#">OpenIdea#${label.name}</a>
                </h2>
                <h6 class="text-muted">when life gives u 100 reasons to cry show life 1000+ reasons to smile !</h6>
            </div>

            <div class="c_center">
                <h1>TotaL <span id="labels_num">${labels_num}</span> LabeL</h1>
                <h5 class="text-muted">sincerely</h5>
            </div>

            <br/>
            <div id="list">
                <table class="table table-hover c_center" style="font-size:16px">
                    <tr>
                        <td><a href="/Blog/index.html"><span class="glyphicon glyphicon-home"></span>
                            &nbsp;&nbsp;首 页</a></td>
                    </tr>
                    <tr>
                        <td><a href="javascript:location.href='/Blog/ArticleServlet?id=0&device='+device+'#article'"><span class="glyphicon glyphicon-film"></span>
                            &nbsp;&nbsp;文 章</a></td>
                    </tr>
                    <tr>
                        <td class="active"><a href="#"><span class="glyphicon glyphicon-tags"></span>
                            &nbsp;&nbsp;标 签</a></td>
                    </tr>
                    <tr>
                        <td><a href="./VersionAjaxServlet?sheet=0"><span class="glyphicon glyphicon-book"></span>
                            &nbsp;&nbsp;历 史</a></td>
                    </tr>
                    <tr>
                        <td><a href="/Blog/page/about.html"><span class="glyphicon glyphicon-user"></span>
                            &nbsp;&nbsp;关 于</a></td>
                    </tr>
                </table>
            </div>
            <br/>
        </div>
        <div class="col-md-1" id="center_content"></div>
        <div class="col-md-7 " id="right_content">
            <br/>
            <div class="list-group">

                <!-- 标题bar -->
                <span style="padding:0" class="list-group-item active"><span class="skew">#标签#</span></span>

                <hr>

                <!-- 列出所有label -->
                <div style="overflow:auto;">

                    <c:forEach var="labelItem" items="${all_tags_ranked}">
                        <div class="label_container">
                            <a class="skew label"
                               href="/Blog/TagsServlet?id=${labelItem.id}"><span>${labelItem.name}</span><span
                                    class="label_number"> :${labelItem.number}</span></a>
                        </div>
                    </c:forEach>

                </div>

                <hr>


                <!-- 开始生成右侧内容 -->
                <c:choose>
                    <c:when test="${id==0}">

                    </c:when>
                    <c:otherwise>
                        <div class="tags_name">
                            <!-- 标签名字 -->
                            <span class="glyphicon glyphicon-triangle-bottom"></span>&nbsp;&nbsp;&nbsp;${label.name}&nbsp;&nbsp;/${label.number}
                        </div>

                        <div>
                            <!-- 文章列表 -->
                            <ul class="list-group">
                                <c:forEach var="article" items="${postListOfTag}">
                                    <li class="list-group-item">
                                        <div>
                                            <a href="javascript:location.href='./ArticleServlet?id=${article.id}&device='+device">《 ${article.title} 》</a>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </div>
                                        <div class="c_right">
                                            <span class="glyphicon glyphicon-stats">&nbsp;${article.length}</span>
                                            &nbsp;&nbsp;
                                            <span class="glyphicon glyphicon-time">&nbsp;${article.time}</span>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>
    </div>

</div>

<br>
<br>
<div class="foot_line"></div>

<br>
<div id="footer">
    <a href="/Blog/TagsServlet?id=0">OpenIdea • Labels</a>
</div>
<br><br>

<script src="/Blog/js/label.js"></script>

</body>
</html>