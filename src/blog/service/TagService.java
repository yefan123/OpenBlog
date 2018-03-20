package blog.service;

import java.util.ArrayList;
import java.util.List;
import blog.dao.TagDao;
import blog.daoImpl.TagDaoImpl;
import blog.model.Article;
import blog.model.Tag;

/**
 * TO web
 */
public class TagService {

	
	private TagDao dao ;
	
	private static TagService instance;
	private static ArticleService as=ArticleService.getInstance();
	
	private TagService(){
		dao = TagDaoImpl.getInstance();	
	}
	
	public static final TagService getInstance(){
		if (instance == null) {
			try {
				instance = new TagService();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;		
	}
	
	public Tag getTagById(String id){
			return dao.getTagById(Integer.parseInt(id));		
	}	
	
	public List<Tag> getAllTag(){
		return dao.getAllTag();
	}

	/**
	 * 获取标签总数
	 * */
	public int getTagCount(){			
		return dao.howManyTags();
	}
	

	public List<Article> getPostList(String tagId) {
		// TODO Auto-generated method stub
		List<Article> list=new ArrayList<Article>();
		List<Integer> idList=dao.getPostListByTag(Integer.parseInt(tagId));
		for (Integer integer : idList) {
			Article article=as.getASmallPostById(integer);
			article.setTime(article.getTime().substring(0, 10));
			list.add(article);
		}
		return list;
	}	
	
	/**
	 * 通过postId获取tagList
	 * */
	public List<Tag> getTagList(int postId) {
		// TODO Auto-generated method stub
		List<Tag> list=new ArrayList<Tag>();
		List<Integer> idList=dao.getTagListByPost(postId);
		for (Integer integer : idList) {
			list.add(dao.getTagById(integer));
		}
		return list;
	}	
	
	public boolean addANewTag(String tagName) {
		return dao.addNewTag(tagName);
	}
	
	public void dropATag(int tagId) {
		dao.deleteTag(tagId);
		dao.dropRelationByTag(tagId);
	}
	
	public List<Tag> getRankedTags(int number){
		return dao.getRankedTags(number);
	}
	
}
