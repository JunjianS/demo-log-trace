package com.cmcc.zeus.sp.trace.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.cmcc.zeus.sp.trace.constant.TraceType;
import com.cmcc.zeus.sp.trace.util.MDCUtil;
import com.cmcc.zeus.sp.trace.util.TraceUtil;

public class ApplyTraceId4ServletFilter implements Filter {
	
	String source = TraceUtil.DEFAULTSOURCE;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		source = filterConfig.getInitParameter("traceSource");
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String traceId = request.getHeader(TraceType.REQUEST.getName());
		
		if (StringUtils.isEmpty(traceId)) {
			traceId = TraceUtil.genUUIDPrefixSource(source);
		}
		
		MDCUtil.put(TraceType.REQUEST, traceId);
		filterChain.doFilter(servletRequest, servletResponse);
		MDCUtil.clear();
	}

	public void destroy() {

	}
}