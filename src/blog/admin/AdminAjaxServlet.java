package blog.admin;

import blog.service.AdminService;
import blog.servlet.MainServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AdminAjaxServlet")
public class AdminAjaxServlet extends HttpServlet {

    /**
     * 管理员的一些操作
     */
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getSession().getAttribute("user") != null) {

            //获取操作选项
            String op = request.getParameter("op");
            //初始化服务对象
            AdminService as = AdminService.getInstance();

            switch (op) {

                case "refresh":
                    MainServlet.refreshApplication();
                    break;

                case "delete_article":
                    String a_id2 = request.getParameter("article_id");
                    as.delteArticle(a_id2);
                    response.sendRedirect("./index.html");
                    break;

                case "tag_delete":
                    String tagId = request.getParameter("tagId");
                    as.dropATag(Integer.parseInt(tagId));
                    break;

                case "add_tag":
                    String tagName = request.getParameter("tagName").trim();
                    as.addATag(tagName);
                    break;

                default:
                    break;

            }

        } else {
            //权限不够
            response.sendError(403);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
