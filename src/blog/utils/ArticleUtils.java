package blog.utils;

import java.util.List;

import blog.model.Article;

/**
 * 文章处理工具
 */
public class ArticleUtils {

    /**
     * 处理下时间
     *
     * @param list
     * @return
     */
    public static List<Article> cutTime(List<Article> list) {

        for (Article a : list) {
            a.setTime(a.getTime().substring(0, 11));
        }

        return list;
    }

    /**
     * 处理下时间(post列表)
     *
     * @param a
     * @return
     */
    public static Article cutTime(Article a) {
        a.setTime(a.getTime().substring(0, 11));
        return a;
    }

    /**
     * 裁剪文章内容,保留444个字符
     *
     * @param list
     * @return
     */
    public static List<Article> cutContent(List<Article> list) {

        for (Article a : list) {
            if (a.getContent() != null && a.getContent().length() > 444) {
                a.setContent(a.getContent().substring(0, 443) + "<br>......");
            }
        }
        return list;
    }

}
