package blog.utils;

import blog.dao.UserDao;
import blog.daoImpl.UserDaoImpl;
import blog.model.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginUtils {

    public static void login(HttpServletRequest request) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
            return;
//        if (username == null || password == null)
//            return;

        UserDao dao = UserDaoImpl.getInstance();
        Admin user = dao.login(username, password);
        if (user == null)
            return;

        //写入session
        HttpSession session = request.getSession();
        //安全起见,管理员session只维持5分钟
        session.setMaxInactiveInterval(5 * 60);
        session.setAttribute("user", user);

    }

}
