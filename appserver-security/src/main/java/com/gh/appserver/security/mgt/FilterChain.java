package com.gh.appserver.security.mgt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FilterChain {
	boolean doFilter(HttpServletRequest request, HttpServletResponse response) throws Exception;
	void addFilter(FilterChain chain) throws Exception ;

}
