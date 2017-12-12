package com.gh.appserver.security.controller;
import org.appserver.bo.UserBo;
import org.appserver.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.xes.live.framework.base.response.APIResult;
import com.xes.live.framework.dao.annotation.DataSource;

@RestController
@RequestMapping("/index")
public class UserController {
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/getUserByID",method=RequestMethod.POST)
	@DataSource(name=DataSource.master)
	public APIResult getUserByID(String id){
		UserBo user = userService.getUserByid(id);
		System.out.println("eeee");
		return new APIResult().success(user);
	}
}
