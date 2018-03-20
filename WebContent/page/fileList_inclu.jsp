<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="blog.service.ArticleService" %>

<% if(request.getParameter("list").equals("big")){ %>

<% ArticleService as = (ArticleService)request.getAttribute("ArticleService"); %>
<% 		request.setAttribute("article_list", as.getArticle(6)); %>


    <div  class="col-md-7 " id="left_Content">
				<br />
				<br />
				<div class="list-group">							
					<span id="list-bar"  style="padding:0"  onclick="smallList(1)" class="skew_father list-group-item active">
						<span id="bar-word" class="skew">摘要 • 近6篇</span>
						<span id="hidden-word" class="skew" >&gt;&gt;&gt;点击查看所有文章&lt;&lt;&lt;</span>
					</span>
					<!-- 这里初始化文章列表 -->
					<div id="realList">
					<c:forEach var="article"   items="${article_list}" >	
					<div class="digest">									
					<h3><a href="/Blog/ArticleServlet?id=${article.id}">${article.title}</a></h3>
					<br/>
					
			<!-- admin here -->
	<c:if test="${sessionScope.user!=null}">
		<span style="color:red">id:${article.id}</span>
		<br>
	</c:if>
					
					<span>${article.length}字符 </span>&nbsp;|&nbsp;
					<span>${article.time}</span>&nbsp;|&nbsp;
					<span>浏览: ${article.visit}</span>
					<br/><br/>					
					<span>${article.content}</span>
					<br/><br/><br/>	
					<a id="readAll" href="/Blog/ArticleServlet?id=${article.id}">阅读全文</a>
					<br/>			
					</div>
					</c:forEach>
					</div>
					<!-- 初始化文章列表完成 -->			
				</div>
				<button id="prevPage"  style="display:none;" onclick="prevPage()">上一页</button>
				<button id="nextPage"  style="display:none;" onclick="nextPage()">下一页</button>
				<br>
				<br>
	</div>
			
<% }else if(request.getParameter("list").equals("small")){ %>


<div  class="col-md-7 " id="left_Content">
				<br />
				<br />
				<div class="list-group">							
					<span id="list-bar"  style="padding:0" class="skew_father list-group-item active">
						<span id="bar-word" class="skew"></span>
						<span id="hidden-word" class="skew" ></span>
					</span>
					<!-- 这里初始化文章列表 -->
					<div id="realList">
						<!-- 之间的内容由下方的函数请求ajax得到 -->
					</div>
					<!-- 初始化文章列表完成 -->			
				</div>
				<button id="prevPage"  style="display:none;" onclick="prevPage()">上一页</button>
				<button id="nextPage"   onclick="nextPage()">下一页</button>
				<br>
				<br>
</div>

<!-- 请求ajax渲染'#realList'的内容 -->
<script>smallList(1);</script>

<% }else{	} %>