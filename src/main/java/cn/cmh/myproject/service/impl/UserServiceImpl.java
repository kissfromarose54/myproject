package cn.cmh.myproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cmh.myproject.entity.User;
import cn.cmh.myproject.mapper.UserMapper;
import cn.cmh.myproject.service.IUserService;
import cn.cmh.myproject.service.ex.InsertDataException;
import cn.cmh.myproject.service.ex.UsernameConflictException;

@Service("userService")
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserMapper userMapper;
	
	public User reg(User user) {
		User result = userMapper.findUserByUsername(user.getUsername());
		System.out.println(result);
		if(result==null) {
			//null��ʾδ�ҵ� ����ע��
			User user1 = insert(user);
			System.out.println(22222);
			return user1;
		}else {
			throw new UsernameConflictException("����ע����û���(" 
					+ user.getUsername() 
					+ ")�Ѿ���ռ�ã�");
		}
		
	}
	
	private User insert(User user) {
		
		Integer row = userMapper.insert(user);
		if(row==1) {
			return user;
		}else {
		   throw new InsertDataException("δ֪����");
		}
		
		
	}
	
}
