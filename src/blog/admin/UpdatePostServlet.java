package blog.admin;

import blog.model.Article;
import blog.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 */
@WebServlet(value = "/UpdatePostServlet")
public class UpdatePostServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("user") != null) {

            AdminService as = AdminService.getInstance();
            Article result = null;
            String option = String.valueOf(request.getParameter("option"));
            switch (option) {
                case "new":
                    result = as.addArticle(request);
                    break;
                case "modify":
                    result = as.updateArticle(request);
                    break;
                default:
                    break;
            }
            request.setAttribute("article", result);

            request.getRequestDispatcher("/admin/result.jsp?type=post").forward(request, response);

        } else {
            //权限不够
            response.sendError(403);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
