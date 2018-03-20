package blog.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import blog.model.Article;
import blog.model.Comment;

public class Form2Bean {
	
	
	public static  Comment commentForm2Bean(HttpServletRequest request) throws FailException{
		
		int id = Integer.valueOf(request.getParameter("id"));
		
		String nickname = request.getParameter("w_nickname");
		String content = request.getParameter("w_content");
		
		Comment bean = new Comment();
		bean.setIp(request.getRemoteAddr());
		bean.setArticle_id(id);
		bean.setNickname(nickname);
		bean.setContent(content);
		if(vilidate(bean)){
			bean.setTime(DateUtils.getFormatDate(new Date()));	
			return bean;
		}
		throw new FailException("Create Fail!");
		
	}
	
	
	private static boolean vilidate(Comment c){
		boolean result= true;
		
		if(c.getArticle_id()==0 || StringUtils.isEmpty(c.getContent())){
			result = false;			
		}
		return result;
	}
	
	
	public static Article articleForm2Bean(HttpServletRequest request) throws FailException{
		
		Map<String, Object> value = new HashMap<String, Object>();
		
		//Article模型没有关于标签的成员属性
		value.put("id", Integer.parseInt(request.getParameter("new_id")));
		value.put("title",request.getParameter("title"));
		value.put("time",request.getParameter("time"));
		value.put("content", request.getParameter("content"));		//为了方便,不进行xss过滤
		value.put("length",request.getParameter("content").length());
		value.put("star", Integer.parseInt(request.getParameter("star")));
		value.put("comment", Integer.parseInt(request.getParameter("comment")));
		value.put("visit",Integer.parseInt(request.getParameter("visit")));
		
		Article bean = new Article();
		
		try {
			BeanUtils.populate(bean, value);
		} catch (Exception e) {		
			e.printStackTrace();
		
		}			
		if( vilidate(bean) ){
			return bean;
		}
		throw new FailException("Create Fail!");
		
	}
	
	private static boolean vilidate(Article a){
		boolean result= true;
		
		if( a.getTitle()==null  || a.getTime()==null){
			result = false;	
		}
		
		return result;
	}
	

}
