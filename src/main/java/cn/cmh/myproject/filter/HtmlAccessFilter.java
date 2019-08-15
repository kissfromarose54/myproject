package cn.cmh.myproject.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 配置访问过滤器 有些页面只有登录后才能够访问 所以配置过滤器
 * @author TEDU
 *
 */
public class HtmlAccessFilter implements Filter{
	
	//集合储存白名单
	private List<String> whileList = new ArrayList<String>();
	
	
	//初始化时加载白名单
    public void init(FilterConfig filterConfig) throws ServletException {
		//确定白名单
    	whileList.add("nike-index.html");
    	whileList.add("reg.html");
    	whileList.add("login.html");
    	System.out.println("无需登录访问的页面：");
    	for (String pages : whileList) {
			System.out.println(pages);
		}
	}
	
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		//获取当前页面 
		//获取路径 对路径进行拆分
		HttpServletRequest request = (HttpServletRequest) arg0;
		String url = request.getRequestURI();
		//num表示的是最后一个/所在位置
		int num = url.lastIndexOf("/")+1;
		String fileName = url.substring(num);
		System.out.println("当前页面是："+fileName);
		
		
		//判断是否需要登录 
		//无需登录 继续执行过滤器链
		if(whileList.contains(fileName)) {
			chain.doFilter(arg0, arg1);
			return;
		}
		
		
		//需要登录 根据session判断是否已经登录
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("uid"));
		

		if(session.getAttribute("uid")!=null) {
			//判断session id 有 放行
			System.out.println("\t已经登录，直接放行");
			chain.doFilter(arg0, arg1);
			return;
		}
		
		//没有 重定向到登录页面
		System.out.println("\t没有登录，重定向到登录页面");
		HttpServletResponse response = (HttpServletResponse) arg1;
		response.sendRedirect("login.html");
	}
	
	//销毁
	public void destroy() {
		
	}

	
	
	

}
