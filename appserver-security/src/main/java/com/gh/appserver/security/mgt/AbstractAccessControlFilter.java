package com.gh.appserver.security.mgt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractAccessControlFilter implements AccessControlFilter {
	

	/**
     * 判断ajax请求
     * 
     * @param request
     * @return
     */
    protected boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }
    
	public void addFilter(AccessControlFilter f){
		
	}


}
