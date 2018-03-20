package blog.servlet;

import blog.model.Article;
import blog.service.ArticleService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class ArticleAjaxServlet
 */
@WebServlet("/ArticleAjaxServlet")
public class ArticleAjaxServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    //每页的文章数目
    public static int number = 25;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        JSONObject jo = new JSONObject();
        int index = 0;
        int page = Integer.parseInt(request.getParameter("page"));
        if (page <= 0) {
            //防止sql注入
        }
        ArticleService as = ArticleService.getInstance();
        List<Article> list = as.getArticlesWithoutContent((page - 1) * number, number);
        for (Article article : list) {
            JSONObject joi = new JSONObject();
            joi.put("title", article.getTitle());
            joi.put("time", article.getTime());
            joi.put("id", article.getId());
            jo.put(index, joi);
            index++;
        }
        response.getWriter().print(jo);


    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
