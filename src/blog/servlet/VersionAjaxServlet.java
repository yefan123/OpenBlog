package blog.servlet;

import blog.model.History;
import blog.service.HistoryService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class VersionAjaxServlet
 */
@WebServlet("/VersionAjaxServlet")
public class VersionAjaxServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final int numberPerSheet = 10;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        int sheet = Integer.parseInt(request.getParameter("sheet"));
        HistoryService hs = HistoryService.getInstance();

        if (sheet == 0) {
            request.getRequestDispatcher("/page/history.jsp").forward(request, response);
        } else {
            StringBuffer jsonArray = new StringBuffer("");
            List<History> versionList = hs.getHistoryList((sheet - 1) * numberPerSheet, numberPerSheet);
            if (versionList.size() == 0) {
                response.getWriter().print("null");
                return;
            } else {
                //循环中尽量用stringBuffer类减少对象数量
                for (History version : versionList) {
                    JSONObject json = new JSONObject();
                    json.put("content", version.getContent());
                    json.put("date", version.getDate());
                    json.put("isBig", version.getIsBig());
                    json.put("version", version.getVersion());
                    jsonArray.append(",").append(json);
                }
            }
            //构造数组形态的文本//删除第一个逗号//java数组直接toString不理想,但jsonObject可以
            jsonArray.delete(0, 1).insert(0, '[').append(']');
            response.getWriter().print(jsonArray);
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
