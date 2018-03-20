<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=yes">
    <title>${article.title}</title>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <link type="text/css" rel="stylesheet" href="./css/public.css"/>
    <link type="text/css" rel="stylesheet" href="./css/article.css"/>
    <!-- 再次注意,所有的jsp都是通过servlet转发的,对browser都是根目录 -->
    <script src="./js/article.js"></script>

</head>
<body>


<!-- admin here -->
<c:if test="${sessionScope.user!=null}">
    <div id="admin">
        <a class="btn btn-warning" href="/Blog/EditorServlet?option=modify&&article_id=${article.id}">
            <span class="glyphicon glyphicon-pencil">&nbsp;&nbsp;编辑(id:${article.id})</span>
        </a>
        <br>
        <button type="button" class="btn btn-warning" onclick="logout()">
            <span class="glyphicon glyphicon-log-out">&nbsp;&nbsp;登出</span>
        </button>
        <br>
        <a onmouseenter="alert('操作不可逆,慎重!')" class="btn btn-danger"
           href="/Blog/AdminAjaxServlet?op=delete_article&&article_id=${article.id}">
            <span class="glyphicon glyphicon-trash">&nbsp;&nbsp;删除该文章</span>
        </a>
    </div>
</c:if>

<!-- 左侧文章列表 -->
<div id="left_bar">
    <c:forEach var="a" items="${visit_rank}">
        <a href="/Blog/ArticleServlet?id=${a.id}" class="list-group-item item">
            <span class="item_title">${a.title}</span><br>
            <span class="glyphicon glyphicon-option-horizontal"> </span>
            <span class="item_time">${a.time}</span>
        </a>
    </c:forEach>
</div>

<!-- 右侧菜单栏 -->
<div id="right_bar">
    <table class="table table-hover c_center">
        <tr>
            <td><a href="/Blog/index.html"><span class="glyphicon glyphicon-home"></span>
                &nbsp;&nbsp;首页</a></td>
        </tr>
        <tr>
            <td class="active"><a href="#article"><span class="glyphicon glyphicon-film"></span>
                &nbsp;&nbsp;文章</a></td>
        </tr>
        <tr>
            <td><a href="/Blog/TagsServlet?id=0"><span class="glyphicon glyphicon-tags"></span>
                &nbsp;&nbsp;标签</a></td>
        </tr>
        <tr>
            <td><a href="./VersionAjaxServlet?sheet=0"><span class="glyphicon glyphicon-book"></span>
                &nbsp;&nbsp;历史</a></td>
        </tr>
        <tr>
            <td><a href="/Blog/page/about.html"><span class="glyphicon glyphicon-user"></span>
                &nbsp;&nbsp;关于</a></td>
        </tr>
    </table>
</div>


<!-- 打赏的遮罩层 -->
<div id="donate_pic" onclick="this.style.display='none'">
    <img width="510" height="691" src="/Blog/img/donate.png">
</div>


<div class="head_line"></div>
<div class="container" id="main">

    <div class="head">
        <div id="title">
            <br>
            <br>
            <br>
            <h2>
                <a href="/Blog/index.html">OpenIdea • 恒星</a>
            </h2>
        </div>
    </div>
    <br>
    <div class="line"></div>


    <div id="article_frame">
        <div id="article">

            <div id="a_head ">
                <br/>
                <h2>${article.title}</h2>
                <br/>
                <div>
                    <h5>
                        <span>${article.time}</span>
                    </h5>
                </div>
                <br>
                <div class="l_div">
                    <h5>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span class="glyphicon glyphicon-stats">&nbsp;${article.length}&nbsp;&nbsp;</span>
                        <span class="glyphicon glyphicon-eye-open">&nbsp;${article.visit}</span>
                    </h5>
                </div>
                <div class="r_div">
                    <h5>
                        <span class="glyphicon glyphicon-star" id="love">&nbsp;${article.star}&nbsp;&nbsp;</span>
                        <span class="glyphicon glyphicon-comment">&nbsp;${article.comment}&nbsp;&nbsp;</span>
                        <span class="glyphicon glyphicon-tags" id="labels_num"></span>
                    </h5>
                </div>

                <br><br>
                <div id="labels">
                    <div style="margin-bottom:1%;font-weight:900;">LABELS</div>
                    <span style="border-top:2px solid #aaa;padding:1%">
					<c:forEach var="t" items="${article_tags}">
                        <a class="skew" style="color:wheat;padding:0.3% 0.8%"
                           href="/Blog/TagsServlet?id=${t.id}">${t.name}</a>&nbsp;
                    </c:forEach>
				</span>
                </div>
            </div>
        </div>


        <div id="a_content">


            <!-- 引入 show.jsp 显示文章内容 -->
            <jsp:include page="/page/show.jsp"/>


        </div>
    </div>

    <div>
        <div class="l_div">
            <span class="glyphicon glyphicon-chevron-left"></span>


            <c:choose>
                <c:when test="${article_pre!=null}">
                    <a href="/Blog/ArticleServlet?id=${article_pre.id}">&nbsp;上一篇:${article_pre.title}</a>
                </c:when>
                <c:otherwise>
                    没有更早的文章啦
                </c:otherwise>
            </c:choose>

            <br>&nbsp;
        </div>
        <div class="r_div">

            <c:choose>
                <c:when test="${article_next!=null}">
                    <a href="/Blog/ArticleServlet?id=${article_next.id}">下一篇:&nbsp;${article_next.title}</a>
                </c:when>
                <c:otherwise>
                    这是最后一篇啦
                </c:otherwise>
            </c:choose>

            <span class="glyphicon glyphicon-chevron-right"></span>
        </div>


        <br/>
        <br/>
    </div>

    <br>
    <br>
    <div class="line"></div>


    <div>
        <a class="action" onclick="love_article(${article.id},this)"><span class="glyphicon glyphicon-thumbs-up">&nbsp;点赞</span></a>
        <a class="action" onclick="document.getElementById('donate_pic').style.display='block'"><span
                class="glyphicon glyphicon-heart">&nbsp;打赏</span></a>
        <a class="action" onclick="document.getElementById('comment').style.display='block';" href="#comment"><span
                class="glyphicon glyphicon-pencil">&nbsp;评论</span></a>
        <br/>
        <br/>
    </div>

    <!-- 加载评论 -->
    <div class="comment">
        <c:if test="${comments!=null}">
            <c:forEach var="comm" varStatus="status" items="${comments}">

                <div class="row">
                    <div style="text-align:left;">
                        <img src="/Blog/img/user.png" height="50" width="50" class="img-circle"/>
                        &nbsp;&nbsp;
                        <span style="color: peru"> ${comm.nickname}</span>
                        &nbsp;&nbsp;发表于
                        <span>&nbsp;&nbsp;${comm.time}</span>
                        <span class="comment_ip glyphicon glyphicon-map-marker">&nbsp;${comm.ip}</span>
                    </div>
                    <div id="c_content" class="c_left">
                        <pre style="padding:1%;">${comm.content }</pre>
                    </div>
                    <div class="r_div">

                        <!-- admin here -->
                        <c:if test="${sessionScope.user!=null}">
                            <button type="button" class="btn btn-danger" onclick="deletecm(this,${comm.id})">删除</button>
                        </c:if>

                    </div>
                </div>

            </c:forEach>

        </c:if>
    </div>


    <!-- 写评论 -->
    <div id="comment" style="display:none">
        <form action="/Blog/NewCommentServlet?id=${article.id}" method="post">
            <input style="width:30%" class="form-control" type="text" name="w_nickname" value="神秘滴网友">
            <br/>
            <textarea class="form-control" style="resize:none; width:100%; height:180px;" name="w_content"></textarea>
            <br/>
            <br/>
            <input class="btn btn-default" type="submit" value="提交" onclick="onclick"/>
            <br/>
            <br/>
        </form>
    </div>

    <div class="line"></div>

</div>
<br><br>
<div id="footer">
    <a href="/Blog/index.html">OpenIdea首页&nbsp;&nbsp;</a>|
    <a href="/Blog/MainServlet?list=small">&nbsp;&nbsp;博文列表</a>
</div>
<br><br><br>

<!-- 回到顶部 -->
<a href="#" class="box">
    <div class="top_arrow glyphicon glyphicon-menu-up"></div>
    <div class="top_str">TOP</div>
</a>

</body>
</html>