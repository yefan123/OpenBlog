package blog.dao;

import blog.model.Admin;

public interface UserDao {

	/**
	 * 注册用户
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	boolean register(String username, String password);

	/**
	 * 登录验证
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	Admin login(String username, String password);

}