/**  
* @Title: NoLoginFilter.java  
* @Package com.qfedu.web.filter  
* @Description: TODO(用一句话描述该文件做什么)  
* @author Feri 
* @date 2018年5月28日  
* @version V1.0  
*/  
package com.qfedu.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
* @Title: NoLoginFilter.java  
* @Package com.qfedu.web.filter  
* @Description: TODO(用一句话描述该文件做什么)  
* @author Feri  
* @date 2018年5月28日  
* @version V1.0  
*/
@WebFilter("/*")
public class NoLoginFilter implements Filter {

	private String[] urls= {"getCart","addCart","order.jsp","orderDetail.jsp","getDirectOrder","addOrder"};
	
	/* */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		String path=request.getServletPath();
		if(isHave(path)) {
			//需要进行登录拦截校验
			if(request.getSession().getAttribute("user")==null) {
				response.sendRedirect("login.jsp");
			}else {
				chain.doFilter(request, response);
			}
		}else {
			chain.doFilter(request, response);
		}
	}
	private boolean isHave(String path) {
		for(String u:urls) {
			if(path.contains(u)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
