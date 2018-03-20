package blog.service;

import java.util.List;
import blog.dao.ArticleDao;
import blog.daoImpl.ArticleDaoImpl;
import blog.model.Article;
import blog.utils.ArticleUtils;

public class ArticleService {

	private ArticleDao dao;

	private static ArticleService instance;
	
	private  ArticleService() {
		dao =  ArticleDaoImpl.getInstance();
	}

	/**
	 * 获取实例
	 * 
	 * @return
	 */
	public static final ArticleService getInstance() {
		if (instance == null) {
			try {
				instance = new ArticleService();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	/**
	 * 获取上一篇文章(small)
	 * @param time
	 * @return
	 */
	public Article  getPreviousArticle(int id){		
		return dao.getANearArticle(id, dao.LESS);		
	}
	/**
	 * 获取下一篇(small)
	 * @param time
	 * @return
	 */
	public Article  getNextArticle(int id){		
		return dao.getANearArticle(id, dao.MORE);		
	}
	
	/**
	 * 获取文章的数量 或 分类的数量
	 * @param search_key
	 * @return
	 */
	public int getCount(String search_key) {
		return dao.getCount(search_key);
	}

	

	/**
	 * 通过列属性获取文章
	 * 
	 * @param column
	 * @param value
	 * @return
	 */
	public List<Article> getArticle(String column, String value) {
		return dao.getArticleByColumn(column, value);
	}
	

	/**
	 * 获取 最新的number篇文章 截取文章长度 截取一下时间 去掉时 分钟 秒
	 * 
	 * @return
	 */
	public List getArticle(int number) {
		List<Article> list = dao.getAllArticle(number);
		for (Article a : list) {
			ArticleUtils.cutContent(list);
			ArticleUtils.cutTime(list);
		}
		return list;
	}
	
	/**
	 * 时间顺序(id顺序)从第start篇起number篇文章列表
	 * 只含有id,title,time
	 * */
	public List<Article> getArticlesWithoutContent(int start,int number) {
		List<Article> list = dao.getArticlesInOnePage(start, number);
		for (Article a : list) {
			ArticleUtils.cutTime(list);
		}
		return list;
	}
	
	

	
	
	public Article getASmallPostById(int postId) {
		return dao.getASmallPost(postId);
	}
	
	
	
	/**
	 * 文章综合排行
	 * @param number 排行榜前number篇
	 * @return 根据visit+star之和排名的ArrayList<Article>
	 * */
	public List<Article> getPostsRank(int number){
		List<Article> list = dao.getPostsRank(number);
		return list;
	}
	
	public int starArticle(int id){
		return dao.star_article(id);
	}
	
	public  void addVisit(int article_id){
		if (article_id<=0) {
			return;
		}
		dao.addVisit(article_id);
	}
	
	public Article getLatestPost() {
		return dao.getLatestArticle();
	}

}
