package blog.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blog.dao.ArticleDao;
import blog.db.C3P0Connection;
import blog.model.Article;
import blog.utils.DBUtils;

/*
 * 文章服务类
 *
 */
public class ArticleDaoImpl implements ArticleDao {

	private Connection conn;	
	private static ArticleDao instance;

	private ArticleDaoImpl() {
		conn = C3P0Connection.getInstance().getConnection();
	}

	public static final ArticleDao getInstance() {
		if (instance == null) {
			try {
				instance = new ArticleDaoImpl();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;

	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#addVisit(int)
	 */
	@Override
	public void addVisit(int article_id) {

		String sql = "update t_article set visit = visit+1 where id = " + article_id;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#getANearArticle(java.lang.String, int)
	 */
	@Override
	public Article getANearArticle(int id, int less_or_more) {

		Article article = null;
		String sql = null;
		//此时只需要id和title...
		if (less_or_more == this.LESS) {
			sql = " SELECT  id,title FROM t_article WHERE id< " + id + "  ORDER BY id DESC LIMIT 1";
		} else if (less_or_more == this.MORE) {
			sql = " SELECT  id,title FROM t_article WHERE id> " + id + "  ORDER BY id LIMIT 1";
		}
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				article = new Article();
				article.setId(rs.getInt("id"));
				article.setTitle(rs.getString("title"));
			}
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#getColumAndCount(java.lang.String)
	 */
	@Override
	public Map getColumAndCount(String search_column) {

		String sql = " select " + search_column + " ,count(" + search_column + ") as counts  from t_article  group by "
				+ search_column;
		Map map = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			map = new HashMap();
			while (rs.next()) {
				map.put(rs.getString(search_column), rs.getInt("counts"));
			}
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#addArticle(blog.model.Article)
	 */
	@Override
	public Article addArticle(Article a) {

		String sql = "insert into t_article(id,title,time,star,comment,visit,content,length) values(?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			if (a.getId()==0) {
				ps.setNull(1, Types.NULL);
			}else {
				ps.setInt(1, a.getId());
			}
			ps.setString(2, a.getTitle());
			ps.setString(3, a.getTime());
			ps.setInt(4, a.getStar());
			ps.setInt(5, a.getComment());
			ps.setInt(6, a.getVisit());
			ps.setString(7, a.getContent());
			ps.setInt(8, a.getLength());
			result = ps.executeUpdate();
			DBUtils.Close(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (a.getId()==0) {
			return this.getLatestArticle();
		}else {
			return a;
		}

	}
	


	/**
	 * 获取最新的文章
	 * 
	 * @return
	 */
	@Override
	public Article getLatestArticle() {

		String sql = "SELECT * FROM t_article ORDER BY id DESC LIMIT 1";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Article article = new Article();
				article.setContent(rs.getString("content"));
				article.setId(rs.getInt("id"));
				article.setStar(rs.getInt("star"));
				article.setTime(rs.getString("time").substring(0, 16));
				article.setTitle(rs.getString("title"));
				article.setVisit(rs.getInt("visit"));
				article.setComment(rs.getInt("comment"));
				article.setLength(rs.getInt("length"));
				DBUtils.Close(ps, rs);
				return article;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#deleteArticle(java.lang.String)
	 */
	@Override
	public boolean deleteArticle(String id) {

		String sql = "delete from t_article where id=?";
		PreparedStatement ps;
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			result = ps.executeUpdate();
			// 关闭连接
			DBUtils.Close(ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result != 0;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#getAllArticle()
	 */
	@Override
	public List getAllArticle(int number) {
		List<Article> list = new ArrayList();

		String sql = "select * from t_article order by id desc limit "+number;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			// bean实例化
			while (rs.next()) {
				Article article = new Article();
				article.setContent(rs.getString("content"));
				article.setId(rs.getInt("id"));
				article.setStar(rs.getInt("star"));
				article.setTime(rs.getString("time"));
				article.setTitle(rs.getString("title"));
				article.setVisit(rs.getInt("visit"));
				article.setComment(rs.getInt("comment"));
				article.setLength(rs.getInt("length"));
				
				list.add(article);
			}
			// 关闭连接
			DBUtils.Close(ps, rs);
			// 排序 article compareTo();
			Collections.sort(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	public List<Article> getArticlesInOnePage(int start,int number) {
		List<Article> list = new ArrayList<Article>();

		String sql = "select id,title,time from t_article order by id desc limit "+start+","+number;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			// bean实例化
			while (rs.next()) {
				Article article = new Article();
				article.setId(rs.getInt("id"));
				article.setTitle(rs.getString("title"));
				article.setTime(rs.getString("time"));
				list.add(article);
			}
			// 关闭连接
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	

	/* 
	 * (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#getVisitRank()
	 */
	@Override
	public List getPostsRank(int number) {
		List<Article> list = new ArrayList();

		String sql = "SELECT id,title,star,visit,time FROM t_article ORDER BY star+visit DESC limit "+number;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			// bean实例化
			while (rs.next()) {
				Article article = new Article();
				article.setId(rs.getInt("id"));
				article.setTitle(rs.getString("title"));
				article.setStar(rs.getInt("star"));
				article.setVisit(rs.getInt("visit"));
				article.setTime(rs.getString("time").substring(2, 10));
				list.add(article);
			}
			// 关闭连接
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#getArticleByColumn(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Article> getArticleByColumn(String column, String value) {
		List<Article> list = null;
		Article article = null;
		String sql = "select * from t_article where " + column + " = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, value);
			ResultSet rs = ps.executeQuery();
			// bean实例化
			list = new ArrayList();
			while (rs.next()) {
				article = new Article();
				article.setComment(rs.getInt("comment"));
				article.setContent(null);
				article.setId(rs.getInt("id"));
				article.setStar(rs.getInt("star"));
				article.setTime(rs.getString("time").substring(0, 16));
				article.setTitle(rs.getString("title"));
				article.setVisit(rs.getInt("visit"));
				article.setLength(rs.getInt("length"));
				article.setContent(rs.getString("content"));
				
				list.add(article);
			}
			// 关闭连接
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#getCount(java.lang.String)
	 */
	@Override
	public int getCount(String search_key) {

		String sql;
		if (search_key.equals(SEARCH_ARTICLE)) {
			sql = "SELECT COUNT(id) FROM t_article";
		} else {
			sql = "SELECT COUNT(id) FROM t_article";		//改改改改改改改改改改改改改改改改改改改改改改改改
		}
		int result = 0;
		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.ArticleDao#star_article(int)
	 */
	@Override
	public int star_article(int id) {

		String sql = "update t_article set star=star+1 where id=" + id;
		int result = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			// DBUtils.Close(conn, ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "select star from t_article where id=" + id;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Article getASmallPost(int postId) {
		// TODO Auto-generated method stub
		String sql = "SELECT id,title,time,star,comment,visit,length from  t_article WHERE id=?";
		PreparedStatement ps;
		Article article=new Article();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, postId);
			ResultSet rs = ps.executeQuery();
			// bean实例化
			while (rs.next()) {
				article.setComment(rs.getInt("comment"));
				article.setContent(null);
				article.setId(rs.getInt("id"));
				article.setStar(rs.getInt("star"));
				article.setTime(rs.getString("time"));
				article.setTitle(rs.getString("title"));
				article.setVisit(rs.getInt("visit"));
				article.setLength(Integer.parseInt(rs.getString("length")));
			}
			// 关闭连接
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}


}

