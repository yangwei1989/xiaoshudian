package com.xiaoshudian.monitor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.xiaoshudian.data.cache.Cache;
import com.xiaoshudian.data.cache.KVCacheProvider;
import com.xiaoshudian.util.StringUtils;

public class GlobalFilter implements Filter {

	private Cache cache = KVCacheProvider.getInstance();
	
    public GlobalFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*��Ӷ�����ʱ��ļ��*/
		request.setAttribute("start", System.currentTimeMillis());
		
		/*��Ӷ��������ͳ��*/
		HttpServletRequest req = (HttpServletRequest)request;
		String path = req.getRequestURI();
		if( path != null && path.endsWith("itdetail.jsp") ){
			//ͳ�Ƶ����
			String id = req.getParameter("id");
			if( id != null && !id.isEmpty() ){
				String key = "click_" + id;
				Object obj = cache.get(key);
				long dec = StringUtils.String2Long(String.valueOf(obj));
				cache.set(key, String.valueOf(dec + 1));
			}
		}
		
		chain.doFilter(req, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
