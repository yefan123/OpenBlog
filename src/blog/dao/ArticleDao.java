package blog.dao;

import java.util.List;
import java.util.Map;

import blog.model.Article;

public interface ArticleDao {
	
	static final String SEARCH_ARTICLE = "article";

	static final int LESS = 1;
	static final int MORE = 2;

	/**
	 * 浏览了文章 增加文章的浏览次数
	 * 
	 * @param article_id
	 */
	void addVisit(int article_id);

	/**
	 * 获取上一文章 或 下一文章
	 * 
	 * @param time
	 * @param less_or_more
	 * @return
	 */
	Article getANearArticle(int id, int less_or_more);

	/**
	 * 分组某一列属性 计算每个组的大小 返回Map
	 * 
	 * @param search_column
	 * @return
	 */
	Map getColumAndCount(String search_column);

	

	/**
	 * 插入新的文章到指定位置(id)
	 * 如果id=0,则插到最默认最新
	 * @param a
	 * @return
	 */
	Article addArticle(Article a);

	/**
	 * 删除文章
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteArticle(String id);

	/**
	 * 获取近number篇文章
	 * 包含content
	 * @return
	 */
	List getAllArticle(int number);
	
	
	

	
	/**
	 * 获取最新的部分文章
	 * 部分:从start开始计数number篇
	 * @return
	 * */
	List getArticlesInOnePage(int start,int number);
	
	
	/**
	 * 
	 * @return 综合排名列表(小的Article)
	 */
	List<Article> getPostsRank(int number);

	/**
	 * 通过某一列查询文章
	 * 
	 * @param column
	 * @param value
	 * @return
	 */
	List<Article> getArticleByColumn(String column, String value);

	/**
	 * 获取文章的数量或者类别的数量
	 * 
	 * @param search_key
	 * @return
	 */
	int getCount(String search_key);

	/**
	 * 点赞了文章
	 * 
	 * @param id
	 * @return
	 */
	int star_article(int id);

	/**
	 * 不含content的post
	 * 
	 * @param postId
	 * @return
	 */
	Article getASmallPost(int postId);

	/**
	 * 获取最新的一篇文章
	 * 
	 * @return
	 */
	Article getLatestArticle();


	
	
}