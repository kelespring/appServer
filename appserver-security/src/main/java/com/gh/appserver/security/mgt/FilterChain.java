package com.gh.appserver.security.mgt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FilterChain {
	public FilterChain addFilter(FilterChain f);
	public String doFilter(HttpServletRequest request, HttpServletResponse response);
}
