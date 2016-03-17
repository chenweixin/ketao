package com.springmvc.ketao.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springmvc.ketao.config.Define;

public class LoginFilter implements Filter {

	private String[] excludedUrls;
	
	public void init(FilterConfig config) throws ServletException {
		String excludes = config.getInitParameter("excludedUrls");
 
        if (excludes != null) {
            this.excludedUrls = excludes.split(",");
        }
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestUri = httpRequest.getRequestURI();
 
        for (String url : excludedUrls) {
            if (requestUri.contains(url.trim()) || requestUri.contains("/ketao/css") ||
            		requestUri.contains("/ketao/js") || requestUri.contains("/ketao/img")) {
                chain.doFilter(request, response);
                return;
            }
        }
        // intercept
        // getSession(true) is required, otherwise there will null pointer at next line.
        HttpSession session = httpRequest.getSession(true);
        if (session.getAttribute(Define.SESSTION_LOGIN_NAME) == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        } else {
            chain.doFilter(request, response);
            return;
        }
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
