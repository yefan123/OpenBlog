package blog.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.model.Comment;
import blog.service.CommentService;
import blog.utils.DateUtils;
import blog.utils.FailException;
import blog.utils.Form2Bean;
import blog.utils.StringUtils;

/**
 * Servlet implementation class NewCommentServlet
 */
@WebServlet("/NewCommentServlet")
public class NewCommentServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int postId=Integer.parseInt(request.getParameter("id"));
		String cookie_name = "comment_on_"+postId;
		
		
		//判断恶意提交(重复提交)
		boolean isRpeat = false;
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(cookie_name)) {
					isRpeat = true;
					break;
				}
			}
		}
		
		//返回的信息		
		String info;		
		Comment cm = null ;
		if( ! isRpeat ){
		//获取对象
		try {
				//request2model
			cm = Form2Bean.commentForm2Bean(request);
			cm.setContent(StringUtils.xssFilter(cm.getContent()));	//XSS过滤
			cm.setNickname(StringUtils.xssFilter(cm.getNickname()));
			CommentService cs = CommentService.getInstance();
			boolean result = cs.addComment(cm);
			if(!result){
				info="意外!评论失败";
			}else{
				info="success!";
			}
		} catch (FailException e) {	
			e.printStackTrace();
			info="评论失败";			
		}
		}else{
			info ="不要重复提交评论哦";
		}
				
		//发送新的cookie
		Cookie c = new Cookie(cookie_name,DateUtils.getFormatDate(new Date()));
		c.setMaxAge(60 * 60);
		c.setPath("/Blog");
		response.addCookie(c);
		
		request.setAttribute("comment", cm);
		request.setAttribute("comment_info", info);
		request.setAttribute("postId", postId);
		request.getRequestDispatcher("/admin/result.jsp?type=comment").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
