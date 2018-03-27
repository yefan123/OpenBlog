package blog.servlet;

import blog.service.TagService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TagsServlet")
public class TagsServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        //String get =  StringUtils.pareCode(request.getParameter("get"));
        TagService ts = TagService.getInstance();
        request.setAttribute("label", ts.getTagById(id));
        request.setAttribute("postListOfTag", ts.getPostList(id));
        request.setAttribute("id", Integer.parseInt(id));    //el表达式只能识别attribute不能是parameter....

        request.getRequestDispatcher("/page/label.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
