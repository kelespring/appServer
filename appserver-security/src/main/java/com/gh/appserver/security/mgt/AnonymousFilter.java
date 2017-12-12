package com.gh.appserver.security.mgt;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.appserver.web.common.CommonConstant;

import com.xes.live.framework.base.enums.ResponseEnums;
import com.xes.live.framework.base.response.APIResult;
import com.xes.live.framework.utils.json.JsonUtil;

public class AnonymousFilter extends AbstractAccessControlFilter {

	@Override
	public boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response, Object mappedValue)
			throws Exception {
		//放行
		return true;
	}

	@Override
	public boolean onAccessDenied(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return false;
	}
 
	
	
	

}
