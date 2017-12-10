package org.appserver.core.service;

import org.appserver.bo.UserBo;
import org.appserver.dao.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService implements IUserService {

	@Autowired
	IUserMapper userDao;
	
	@Override
	public UserBo getUserByid(String id) {
		System.out.println("hellocore3333333333333333333333333");
		return userDao.getUserByid(id);
	}

}
