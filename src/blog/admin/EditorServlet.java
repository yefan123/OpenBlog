package blog.admin;

import blog.model.Article;
import blog.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(value = "/EditorServlet")
public class EditorServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("user") != null) {

            String option = request.getParameter("option");
            AdminService as = AdminService.getInstance();
            request.setAttribute("option",option);

            if (option.equals("new")) {
                //初始化时间
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Article edit_article = new Article();
                edit_article.setTime(df.format(new Date()));
                edit_article.setId(0);
                request.setAttribute("title", "写文章");
                request.setAttribute("edit_article",edit_article);
            } else if (option.equals("modify")) {
                String postId = request.getParameter("article_id");
                request.setAttribute("edit_article", as.getArticle(postId));
                request.setAttribute("title", "编辑文章");
            } else {
            }

            request.getRequestDispatcher("./admin/editor.jsp").forward(request, response);

        } else {
            //权限不够
            response.sendError(403);
        }

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
