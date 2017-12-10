package org.appserver.web.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xes.live.framework.dao.annotation.DataSource;
import com.xes.live.framework.dao.dbroute.RouteHolder;



public class DataSourceInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = LogManager.getLogger(DataSourceInterceptor.class);
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		 HandlerMethod handlerMethod = (HandlerMethod) handler;
	     Method method = handlerMethod.getMethod();
	     DataSource datasource = method.getAnnotation(DataSource.class);
	     if(null==datasource){
	      log.warn("未设置数据源:"+method);
	      RouteHolder.setRouteKey(DataSource.master);
	     }else{
	      RouteHolder.setRouteKey(datasource.name());	 
	     }
		return true;
	}
}
