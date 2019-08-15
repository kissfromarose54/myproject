package cn.cmh.myproject.service;

import org.apache.ibatis.annotations.Param;

import cn.cmh.myproject.entity.User;

public interface IUserService {
	User reg(User user);
	
	User login(@Param(value = "username") String username,
			   @Param(value = "password") String password);
}
