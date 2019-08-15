package cn.cmh.myproject.mapper;

import cn.cmh.myproject.entity.User;

public interface UserMapper {
	Integer insert(User user);
	
	User findUserByUsername(String username);
	
}
