<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=yes">

    <title>首页 | 恒星的博客</title>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <link type="text/css" rel="stylesheet" href="./css/public.css"/>
    <link type="text/css" rel="stylesheet" href="./css/main.css"/>

    <script src="./js/main.js"></script>

    <script>
        const article_number = ${article_number};
        const arti_page_num = ${arti_page_num};
        let page = 1;
    </script>

</head>
<body>

<!-- admin here -->
<c:if test="${sessionScope.user!=null}">
    <div id="admin">
        <a class="btn btn-warning" href="/Blog/EditorServlet?option=new">
            <span class="glyphicon glyphicon-plus">&nbsp;写文章</span>
        </a>
        <br>
        <button class="btn btn-warning" onclick="refresh()">
            <span class="glyphicon glyphicon-refresh">&nbsp;刷新</span>
        </button>
        <br>
        <button type="button" class="btn btn-warning" onclick="logout()">
            <span class="glyphicon glyphicon-log-out">&nbsp;登出</span>
        </button>

    </div>
</c:if>

<div class="head_line"></div>

<div class="container" id="main">

    <div id="header"></div>

    <div class="row c_center">

        <!-- 静态导入首页左侧的文章列表 -->
        <%@include file="fileList_inclu.jsp" %>

        <div class="col-md-1" id="center_content">
        </div>

        <div class="col-md-4" id="right_content">


            <div id="title">
                <h1><a href="#">恒星's Blog</a></h1>
                <h5 class="text-muted">www.openidea.com</h5>
            </div>

            <br/>

            <div class="c_center" id="person_info">
                <a href="/Blog/page/about.html"><img style="background:white" src="/Blog/img/header.png" height="230"
                                                     width="230"
                                                     alt="我的logo哪去了?" class="img-circle"></a>
                <h5 class="text-muted">Version <b>22.7</b> since <b>2018.03</b></h5>
                <h6 class="text-muted">Welcome!</h6>
            </div>

            <br>

            <!-- 目录 -->
            <div id="list">

                <ul class="c_center">
                    <li><a href="#"><span class="glyphicon glyphicon-home">
								&nbsp;首页 | ${visited}/访问</span></a></li>

                    <li><a href="/Blog/MainServlet?list=small"><span class="glyphicon glyphicon-film">
								&nbsp;文章 | ${article_number}/日志</span></a></li>

                    <li><a href="/Blog/TagsServlet?id=0"><span class="glyphicon glyphicon-tags">
								&nbsp;标签 | ${labels_num}/分类</span></a></li>

                    <li><a href="./VersionAjaxServlet?sheet=0"><span class="glyphicon glyphicon-book">
								&nbsp;历史 | ${versions_num}/版本</span></a></li>

                    <li><a href="/Blog/page/about.html"><span class="glyphicon glyphicon-user">
								&nbsp;关于 | ${ageDays}/日夜</span></a></li>
                </ul>
            </div>

            <br/>
            <br/>
            <br/>
            <br/>

            <div class="visit">
                <div class="list-group">
                    <span style="padding:0" class="list-group-item active"><span class="skew">阅读排行</span></span>
                    <!-- 这里初始化阅读排行 -->
                    <div id="nth">
                        <% int i = 0; %>
                        <c:forEach var="a" items="${visit_rank}">
                            <% i++;    //进位 %>
                            <% if (i > 15) break;    //判断 %>

                            <a href="/Blog/ArticleServlet?id=${a.id}" class="list-group-item"><span
                                    class="nth"><%=i %>.</span><span
                                    class="rank_count">(${a.visit+a.star})</span>${a.title}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <br>

            <div class="labels">
                <div class="list-group">
                    <span style="padding:0" class="list-group-item active"><span class="skew">热门标签</span></span>
                    <!-- 这里初始化热门标签 -->
                    <% int j = 0; %>
                    <c:forEach var="t" items="${all_tags_ranked}">
                        <% j++; %>
                        <% if (j > 10) break; %>
                        <a href="/Blog/TagsServlet?id=${t.id}" class="list-group-item">#${t.name}&nbsp;&nbsp;&nbsp;&nbsp;
                            /${t.number}<!-- 这里放数目 --></a>
                    </c:forEach>
                    <!-- 初始化结束 -->
                </div>
            </div>


            <!-- 这里放外链 -->


        </div>


    </div>
    <br>
    <br>
    <div class="foot_line"></div>
</div>
<br><br>
<div id="footer">
    <a href="https://github.com/JinHengyu">开源の博客&nbsp;<img src="/Blog/img/github.png" width="20px"
                                                           height="20px"></a>&nbsp;|&nbsp;<a
        href="#">openidea.xin</a>
    <br/>
    Copyright © 2017~2018 | <img src="/Blog/img/police.png" width="20px" height="20px">&nbsp;苏ICP备17063353号
</div>
<br/><br/><br/>

<!-- 回到顶部 -->
<a href="#" class="box">
    <div class="top_arrow glyphicon glyphicon-menu-up"></div>
    <div class="top_str">TOP</div>
</a>

</body>
</html>