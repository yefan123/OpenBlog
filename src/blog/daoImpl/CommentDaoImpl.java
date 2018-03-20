package blog.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.dao.CommentDao;
import blog.db.C3P0Connection;
import blog.model.Comment;
import blog.utils.DBUtils;

public class CommentDaoImpl implements CommentDao {

	private Connection conn;

	private CommentDaoImpl() {
		conn = C3P0Connection.getInstance().getConnection();
	}

	private static CommentDao instance;

	public static CommentDao getInstance() {
		if (instance == null) {
			try {
				instance = new CommentDaoImpl();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see blog.daoImpl.CommentDao#deleteComment(int)
	 */
	@Override
	public boolean deleteComment(int comment_id) {

		String sql = "DELETE FROM t_comment WHERE id=" + comment_id;
		int result = 0;
		try {
			// 文章-1评论
			article_sub_comemnt(conn, comment_id);
			PreparedStatement ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
			DBUtils.Close(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result > 0;
	}

	/**
	 * 文章的评论-1
	 * 
	 * @param conn
	 * @param comment_id
	 */
	private void article_sub_comemnt(Connection conn, int comment_id) {

		String sql = "SELECT  article_id FROM t_comment WHERE id =" + comment_id;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int article_id = 0;
			if (rs.next()) {
				article_id = rs.getInt("article_id");
			}
			sql = "UPDATE t_article SET COMMENT=COMMENT-1 WHERE id=" + article_id;
			conn.prepareStatement(sql).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 文章评论+1
	 * 
	 * @see blog.daoImpl.CommentDao#addComment(blog.model.Comment)
	 */
	@Override
	public boolean addComment(Comment comment) {

		Connection conn = C3P0Connection.getInstance().getConnection();
		String sql = "INSERT  INTO t_comment(article_id,nickname,content,time,ip) VALUES(?,?,?,?,?)";
		int result = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, comment.getArticle_id());
			ps.setString(2, comment.getNickname());
			ps.setString(3, comment.getContent());
			ps.setString(4, comment.getTime());
			ps.setString(5, comment.getIp());
			result = ps.executeUpdate();

			sql = "UPDATE t_article SET COMMENT = COMMENT+1  WHERE id=" + comment.getArticle_id();
			PreparedStatement ps2 = conn.prepareStatement(sql);
			ps2.executeUpdate();

			DBUtils.Close(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result > 0;
	}


	@Override
	public List<Comment> getCommentList(int article_id) {

		String sql = "SELECT * FROM t_comment WHERE article_id=? "; // ORDER BY time
		List<Comment> list = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, article_id);
			ResultSet rs = ps.executeQuery();
			Comment cm;
			list = new ArrayList<Comment>();
			while (rs.next()) {
				cm = new Comment();
				cm.setId(rs.getInt("id"));
				cm.setArticle_id(rs.getInt("article_id"));
				cm.setNickname(rs.getString("nickname"));
				cm.setTime(rs.getString("time").substring(0, 16));
				cm.setContent(rs.getString("content"));
				cm.setIp(rs.getString("ip"));
				list.add(cm);
			}
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
