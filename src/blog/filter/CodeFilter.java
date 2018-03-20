package blog.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CodeFilter
 */
@WebFilter(filterName="codeFilter",urlPatterns={"/*"})
public class CodeFilter implements Filter {

   
    public CodeFilter() {
        // TODO Auto-generated constructor stub
    }
	
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		HttpServletRequest rq = (HttpServletRequest) request;
		HttpServletResponse rp=(HttpServletResponse) response;
		rq.setCharacterEncoding("utf-8");
		rp.setCharacterEncoding("utf-8");
		rp.setContentType("text/html;charset=utf-8");	
		
		//设置浏览器缓存3小时
		rp.setHeader("Cache-Control", "public");  
		rp.setHeader("Pragma", "Pragma");  
		rp.setDateHeader("expires", new Date().getTime()+3*60*60*1000);  
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
