package blog.daoImpl;

import blog.dao.HistoryDao;
import blog.db.C3P0Connection;
import blog.model.History;
import blog.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDaoImpl implements HistoryDao {

    private static HistoryDao instance;
    private Connection conn;

    private HistoryDaoImpl() {
        conn = C3P0Connection.getInstance().getConnection();
    }

    public static HistoryDao getInstance() {
        if (instance == null) {
            try {
                instance = new HistoryDaoImpl();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;

    }


    @Override
    public List<History> getHistoryList(int start, int length) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM t_history ORDER BY id DESC LIMIT ?,?";
        List list = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, length);
            ResultSet rs = ps.executeQuery();
            History his;
            list = new ArrayList();
            while (rs.next()) {
                his = new History();
                his.setContent(rs.getString("content"));
                his.setDate(rs.getString("date"));
                his.setId(rs.getInt("id"));
                his.setIsBig(rs.getBoolean("isBig"));
                his.setVersion(rs.getString("version"));
                list.add(his);
            }
            DBUtils.Close(ps, rs);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }


    @Override
    public int howManyVersions() {
        // TODO Auto-generated method stub
        String sql = "select count(id) from t_history";
        int number = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                number = rs.getInt(1);
            }
            DBUtils.Close(ps, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return number;
    }
}
