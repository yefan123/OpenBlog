package blog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.dao.ArticleDao;
import blog.db.VisitorDB;
import blog.service.ArticleService;
import blog.service.TagService;
import blog.utils.DateUtils;


/**
 * 容器启动时加载
 * 整个网站的唯一入口
 * 因为tomcat每天重启一次,故没有在容器内部定时刷新计算attribute
 */
@WebServlet(value = "/MainServlet", loadOnStartup = 1)
public class MainServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;
    private static ServletContext application = null;

    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub
        // 获取application对象
        application = this.getServletContext();
        refreshApplication();
    }

    /**
     * 更新application属性
     */
    public static boolean refreshApplication() {
        if (application == null) {
            return false;
        }
        // 访客数量
        application.setAttribute("visited", VisitorDB.totalVisit());
        // 博龄
        application.setAttribute("ageDays", DateUtils.getAgeDays());

        ArticleService as = ArticleService.getInstance();
        // 阅读排行
        application.setAttribute("visit_rank", as.getPostsRank(20));
        // 文章数量
        application.setAttribute("article_number", as.getCount(ArticleDao.SEARCH_ARTICLE));

        // 历史版本数量-->动态转静态
        //HistoryService hs = HistoryService.getInstance();
        //application.setAttribute("versions_num", hs.getVersionsCount());
        application.setAttribute("versions_num", 75);

        TagService ts = TagService.getInstance();
        // 标签数量
        application.setAttribute("labels_num", ts.getTagCount());
        // 所有标签(排名后)
        application.setAttribute("all_tags_ranked", ts.getRankedTags(100));

        // 每一页文章的数目
        application.setAttribute("arti_page_num", ArticleAjaxServlet.number);

        return true;
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        if (request.getSession().getAttribute("user") != null) {
//            refreshApplication();
//        }

        ArticleService as = ArticleService.getInstance();
        request.setAttribute("ArticleService", as);

        // 初始化文章列表
        // request.setAttribute("article_list", as.getArticle());
        // //放到fileList_inclu.jsp中了


        // 初始化用户已读列表
        if (request.getSession().getAttribute("readList") == null) {
            request.getSession().setAttribute("readList", new ArrayList<Integer>());
        }

        // 转发到 博客主页 界面
        if (request.getParameter("list").equals("small")) {
            request.getRequestDispatcher("/page/main.jsp?list=small").forward(request, response);
        } else {
            request.getRequestDispatcher("/page/main.jsp?list=big").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
