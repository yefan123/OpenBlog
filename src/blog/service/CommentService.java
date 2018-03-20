package blog.service;

import java.util.List;

import blog.dao.CommentDao;
import blog.daoImpl.CommentDaoImpl;
import blog.model.Comment;

public class CommentService {

	
	private CommentDao dao;

	private static CommentService instance;
	private CommentService(){	
		dao =  CommentDaoImpl.getInstance();
	}
	
	public static final CommentService getInstance(){
		if (instance == null) {
			try {
				instance = new CommentService();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	
	/**
	 * 通过article_id获取所有评论
	 * */
	public List<Comment> loadComment(int article_id){
		return dao.getCommentList(article_id);
	}
	
	public boolean addComment(Comment comment){
		return dao.addComment(comment);		
	}
	


	public boolean deleteComment(int id){
		return dao.deleteComment(id);
	}
}
