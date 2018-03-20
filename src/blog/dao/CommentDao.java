package blog.dao;

import java.util.List;

import blog.model.Comment;

public interface CommentDao {

	/**
	 * 删除评论
	 * 
	 * @param comment_id
	 * @return
	 */
	boolean deleteComment(int comment_id);

	/**
	 * 新的评论
	 * 
	 * @param comment
	 * @return
	 */
	boolean addComment(Comment comment);

	/**
	 * 获取某篇文章的所有评论
	 * 
	 * @param article_id
	 * @return
	 */
	List<Comment> getCommentList(int article_id);

	

}