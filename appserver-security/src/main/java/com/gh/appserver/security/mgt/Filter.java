package com.gh.appserver.security.mgt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Filter {
	void doFilter(HttpServletRequest request, HttpServletResponse response,Filter chain) throws Exception;
}
