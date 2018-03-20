package blog.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.dao.TagDao;
import blog.db.C3P0Connection;
import blog.model.Tag;
import blog.utils.DBUtils;

/**
 * 文章的标签类
 * 该dao类操作两张表,分别是labels和label_relation
 */
public class TagDaoImpl implements TagDao {

	private Connection conn;

	private static TagDao instance;

	private TagDaoImpl() {
		conn = C3P0Connection.getInstance().getConnection();
	}

	public static final TagDao getInstance() {
		if (instance == null) {
			try {
				instance = new TagDaoImpl();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.TagDao#addTag(int, java.lang.String)
	 */
	@Override
	public boolean addRelation(int tagId, int postId) {

		String sql = "insert into label_relation(tagId,postId) values(?,?)";
		int result = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, tagId);
			ps.setInt(2, postId);
			result = ps.executeUpdate();
			DBUtils.Close(ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result != 0;
	}
	
	
	@Override
	public boolean updateTagNumber(int id, int delta) {

		String sql1 = "UPDATE labels SET number=number+? WHERE id=?";
		String sql2 = "UPDATE labels SET number=number-? WHERE id=?";
		int result = 0;
		try {
			PreparedStatement ps ;
			if (delta>0) {
				ps = conn.prepareStatement(sql1);
			}else {
				ps = conn.prepareStatement(sql2);
			}
			ps.setInt(1, Math.abs(delta));
			ps.setInt(2, id);
			result = ps.executeUpdate();
			DBUtils.Close(ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result != 0;
	}
	
	
	@Override
	public boolean addNewTag(String name) {

		String sql = "insert into labels(name,number) values(?,?)";
		int result = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, 0);
			result = ps.executeUpdate();
			DBUtils.Close(ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result != 0;
	}

	/* (non-Javadoc)
	 * @see blog.daoImpl.TagDao#deleteTag(int, java.lang.String)
	 */
	@Override
	public boolean deleteTag(int id) {

		String sql = "delete from labels where id=?";
		int result = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			result = ps.executeUpdate();
			DBUtils.Close(ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result != 0;
	}
	
	
	@Override
	public boolean dropRelationByTag(int tagId) {

		String sql = "delete from label_relation where tagId=?";
		int result = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, tagId);
			result = ps.executeUpdate();
			DBUtils.Close(ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result != 0;
	}
	
	
	
	@Override
	public boolean dropRelationByPost(int postId) {

		String sql = "delete from label_relation where postId=?";
		int result = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, postId);
			result = ps.executeUpdate();
			DBUtils.Close(ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result != 0;
	}
	

	/* (non-Javadoc)
	 * @see blog.daoImpl.TagDao#getAllTag()
	 */
	@Override
	public List<Tag> getAllTag() {

		String sql = "SELECT  *  FROM labels";
		List<Tag> list = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			list = new ArrayList<Tag>();
			Tag tag;
			while (rs.next()) {
				tag = new Tag();
				tag.setId(rs.getInt("id"));
				tag.setName(rs.getString("name"));
				tag.setNumber(rs.getInt("number"));
				list.add(tag);
			}
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	

	
	

	

	@Override
	public boolean addTag(String name) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO labels(name) values(?)";
		int result = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			result = ps.executeUpdate();
			DBUtils.Close(ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result != 0;
	}



	

	@Override
	public List<Integer> getPostListByTag(int tagId) {
		// TODO Auto-generated method stub
		String sql = "select * from label_relation where tagId="+tagId;
		List<Integer> list = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			list = new ArrayList<Integer>();
			while (rs.next()) {
				list.add(rs.getInt("postId"));
			}
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<Integer> getTagListByPost(int postId) {
		// TODO Auto-generated method stub
		String sql = "select * from label_relation where PostId="+postId;
		List<Integer> list = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			list = new ArrayList<Integer>();
			while (rs.next()) {
				list.add(rs.getInt("tagId"));
			}
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int howManyTags() {
		// TODO Auto-generated method stub
		String sql = "select count(id) from labels";
		int number = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				number=rs.getInt(1);
			}
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return number;
	}


	@Override
	public Tag getTagById(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from labels WHERE id=?";
		Tag tag=new Tag();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tag.setId(rs.getInt("id"));
				tag.setName(rs.getString("name"));
				tag.setNumber(rs.getInt("number"));
			}
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tag;
	}

	@Override
	public List<Tag> getRankedTags(int number) {
		// TODO Auto-generated method stub
		String sql = "SELECT  *  FROM labels ORDER BY number DESC LIMIT "+number;
		List<Tag> list = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			list = new ArrayList<Tag>();
			Tag tag;
			while (rs.next()) {
				tag = new Tag();
				tag.setId(rs.getInt("id"));
				tag.setName(rs.getString("name"));
				tag.setNumber(rs.getInt("number"));
				list.add(tag);
			}
			DBUtils.Close(ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
