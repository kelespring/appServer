package org.appserver.dao;

import org.appserver.bo.UserBo;

public interface IUserMapper {
	UserBo getUserByid(String id);
}
