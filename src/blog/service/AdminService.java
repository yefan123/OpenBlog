package blog.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import blog.dao.ArticleDao;
import blog.dao.TagDao;
import blog.daoImpl.ArticleDaoImpl;
import blog.daoImpl.TagDaoImpl;
import blog.model.Article;
import blog.utils.FailException;
import blog.utils.Form2Bean;

public class AdminService {

	private ArticleDao adao;
	private TagDao tdao;
	private static AdminService instance;

	private AdminService() {
		adao = ArticleDaoImpl.getInstance();
		tdao = TagDaoImpl.getInstance();	
	}

	/**
	 * 获取实例
	 * 
	 * @return
	 */
	public static final AdminService getInstance() {
		if (instance == null) {
			try {
				instance = new AdminService();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	/**
	 * 返回一个刚创建的smallPost
	 * 
	 * @return
	 */
	public Article addArticle(HttpServletRequest request){
		//新建文章
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}		
		Article new_article=null ;
		try {
			new_article = Form2Bean.articleForm2Bean(request);
		} catch (FailException e) {
			e.printStackTrace();
		}		
		if(new_article==null) 
			return null;
		Article a = adao.addArticle(new_article);			
		if(a==null) return null;
		
		//增加标签
		String str = request.getParameter("tags").trim();				
		String [] tags = str.split(" ");
		for(String tagId:tags){
			tdao.addRelation(Integer.parseInt(tagId), a.getId());			
			tdao.updateTagNumber(Integer.parseInt(tagId), +1);
		}				
		return adao.getASmallPost(a.getId());			
	}
	
	public Article updateArticle(HttpServletRequest request){
		//获取旧的文章id
		String old_id = request.getParameter("old_id");
		//删除旧的文章
		this.delteArticle(old_id);		
				
		return this.addArticle(request);		
	}
	
	
	public Article getArticle(String article_id){
		 List<Article> list = adao.getArticleByColumn("id", article_id);
		 if(list.size()!=0){
			 return list.get(0);
		 }
		return null;
	}

	public boolean delteArticle(String id){
		List<Integer> idList=tdao.getTagListByPost(Integer.parseInt(id));
		for (Integer integer : idList) {
			tdao.updateTagNumber(integer, -1);
		}
		tdao.dropRelationByPost(Integer.parseInt(id));
		return adao.deleteArticle(id);
	}
	
	
	
	
	public void dropATag(int tagId) {
		tdao.deleteTag(tagId);
		tdao.dropRelationByTag(tagId);
	}
	
	
	public boolean addATag(String tagName) {
		return tdao.addNewTag(tagName);
	}



}
