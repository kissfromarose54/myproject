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
 * ���÷��ʹ����� ��Щҳ��ֻ�е�¼����ܹ����� �������ù�����
 * @author TEDU
 *
 */
public class HtmlAccessFilter implements Filter{
	
	//���ϴ��������
	private List<String> whileList = new ArrayList<String>();
	
	
	//��ʼ��ʱ���ذ�����
    public void init(FilterConfig filterConfig) throws ServletException {
		//ȷ��������
    	whileList.add("nike-index.html");
    	whileList.add("reg.html");
    	whileList.add("login.html");
    	System.out.println("�����¼���ʵ�ҳ�棺");
    	for (String pages : whileList) {
			System.out.println(pages);
		}
	}
	
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		//��ȡ��ǰҳ�� 
		//��ȡ·�� ��·�����в��
		HttpServletRequest request = (HttpServletRequest) arg0;
		String url = request.getRequestURI();
		//num��ʾ�������һ��/����λ��
		int num = url.lastIndexOf("/")+1;
		String fileName = url.substring(num);
		System.out.println("��ǰҳ���ǣ�"+fileName);
		
		
		//�ж��Ƿ���Ҫ��¼ 
		//�����¼ ����ִ�й�������
		if(whileList.contains(fileName)) {
			chain.doFilter(arg0, arg1);
			return;
		}
		
		
		//��Ҫ��¼ ����session�ж��Ƿ��Ѿ���¼
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("uid"));
		

		if(session.getAttribute("uid")!=null) {
			//�ж�session id �� ����
			System.out.println("\t�Ѿ���¼��ֱ�ӷ���");
			chain.doFilter(arg0, arg1);
			return;
		}
		
		//û�� �ض��򵽵�¼ҳ��
		System.out.println("\tû�е�¼���ض��򵽵�¼ҳ��");
		HttpServletResponse response = (HttpServletResponse) arg1;
		response.sendRedirect("login.html");
	}
	
	//����
	public void destroy() {
		
	}

	
	
	

}
