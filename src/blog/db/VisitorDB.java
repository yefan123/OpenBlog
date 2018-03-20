package blog.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import blog.utils.DBUtils;
import blog.utils.DateUtils;

public class VisitorDB {

	
	private static final Connection conn = C3P0Connection.getInstance().getConnection();		
	/**
	 * 浏览者信息
	 */
	public  static void visit(HttpServletRequest request){
			
		
		String IP = request.getRemoteAddr();	//得到来访者的IP地址		
		if (IP==null) {
			IP=request.getHeader("X-Forwarded-For");
		}
		String time =DateUtils.getFormatDate(new Date());	 
		int port=request.getRemotePort();	//客户端口
		String browser=request.getHeader("User-Agent");	//浏览器信息
		
		
		String sql ="insert into t_visitor(ip,time,port,browser) values(?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, IP);
			ps.setString(2, time);
			ps.setInt(3, port);
			ps.setString(4, browser);
			ps.executeUpdate();
			DBUtils.Close(ps);
		} catch (SQLException e) {		
			e.printStackTrace();
		}		 	 	
	}
	
	/**
	 * 全部浏览者
	 * @return
	 */
	public static int totalVisit(){
		Connection conn = C3P0Connection.getInstance().getConnection();		
		int result = 0;
		String sql ="select count(id) from t_visitor";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			DBUtils.Close(ps,rs);
		} catch (SQLException e) {		
			e.printStackTrace();
		}		 	 
		return result;		
	}
	
	
	
	
}
