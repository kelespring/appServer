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

public class AuthenticationFilter extends AbstractAccessControlFilter {

	@Override
	public boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response, Object mappedValue)
			throws Exception {
		// 已经登陆
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(CommonConstant.SESSION_USEROBJ_KEY);
		if(obj!=null){
			if(obj instanceof UserSession){
				UserSession userSession = (UserSession)obj;
				if(userSession.isLogin()){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean onAccessDenied(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (isAjax(request)) {
			//返回JSON
			APIResult apiResult = new APIResult().failure(ResponseEnums.ERROR_103.getCode(), "您尚未登录或登录时间过长,请重新登录!");
			String resJson = JsonUtil.ObjectToJson(apiResult);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(resJson);
		}else{
	        String  url=request.getServletPath().toString();
			String contextPath=request.getContextPath();
			//被拦截，重定向到login界面
            response.sendRedirect(contextPath+"/login.htm?ReturnUrl="
                    + URLEncoder.encode(url,"UTF-8"));
		}
		return false;
	}
 
	
	
	

}
