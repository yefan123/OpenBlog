package blog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.model.Article;
import blog.service.ArticleService;
import blog.service.CommentService;
import blog.service.TagService;

@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//想要获取的文章 id
		int id= Integer.parseInt(request.getParameter("id"));
		ArticleService as =  ArticleService.getInstance();		
		
		//文章
		Article a=null;
		if (id==0) {
			a=as.getLatestPost();	//id==0表示想要最新的文章
		}else {
			a = as.getArticle("id",id+"").get(0);
		}
		request.setAttribute("article",a);		
		
		//点开文章 自动 增加 浏览次数
		if (request.getSession().getAttribute("readList")==null) {
			//do nothing
		}else {
			List<Integer> readList=(ArrayList<Integer>)(request.getSession().getAttribute("readList"));
			if (readList.contains(a.getId())) {
				//do nothing
			}else {
				as.addVisit(a.getId());
				readList.add(a.getId());
			}
		}
		
		//文章的所有标签
		TagService ts = TagService.getInstance();
		request.setAttribute("article_tags",ts.getTagList(a.getId()));
		//获取上一篇文章
		request.setAttribute("article_pre", as.getPreviousArticle(a.getId()));	
		//获取下一篇文章
		request.setAttribute("article_next", as.getNextArticle(a.getId()));		
		//加载文章评论
		CommentService  cs = CommentService.getInstance();
		request.setAttribute("comments",cs.loadComment(a.getId()));
		
		request.getRequestDispatcher("/page/article.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
