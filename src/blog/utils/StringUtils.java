package blog.utils;


public class StringUtils {

    public static boolean isEmpty(String str) {

        return (str == null || str.trim().equals(""));
    }


    //解码 解决在URL传中文值出现的乱码问题	//现在没卵用了
//		public static String pareCode(String str) throws UnsupportedEncodingException{
//			return new String(str.getBytes("ISO-8859-1"),"UTF-8");
//		}

    /**
     * xss过滤<,>,&,',",/这6个html字符
     */
    public static String xssFilter(String content) {
        return content.replaceAll("<", "《").replaceAll(">", "》").replaceAll("&", "#").replaceAll("\"", "``").replaceAll("\'", "`").replaceAll("/", "|");
    }
}
