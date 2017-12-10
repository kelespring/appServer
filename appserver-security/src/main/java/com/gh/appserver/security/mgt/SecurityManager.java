package com.gh.appserver.security.mgt;

/**
 * 权限管理器
 * 注入系统资源访问规则
 * 注入过滤器
 * 注入资源访问权限失败URL，登录URL
 * 提供对外：
 * hasPermissions(url)
 * 内部
 * @author kevin
 * 
 */
public abstract class SecurityManager {
	/**
	 * 权限规则,路径+过滤器
	 * 匹配规则：hash.get(url)是否命中（为了速度），没有命中就顺序遍历LIST（注意带**的startWith判断）
	 * 一旦匹配到就getBean("filterName")，执行对应的isAccessAllowed||onAccessDenied，返回true表示执行成功
	 * 配置注意：粒度小的在前
	 * /home=user
	 * /authenticated=authc
	 * /permission=authc
	 * static/**=anon
	 * /**=anon
	 */
	//权限控制器
	
}
