package cn.cmh.myproject.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.exceptions.PasswordExpiredException;

import cn.cmh.myproject.entity.User;
import cn.cmh.myproject.mapper.UserMapper;
import cn.cmh.myproject.service.IUserService;
import cn.cmh.myproject.service.ex.InsertDataException;
import cn.cmh.myproject.service.ex.UsernameConflictException;
import cn.cmh.myproject.service.ex.usernameNotFindException;

@Service("userService")
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserMapper userMapper;
	
	public User reg(User user) {
		User result = userMapper.findUserByUsername(user.getUsername());
		System.out.println(result);
		if(result!=null) {
			throw new UsernameConflictException("尝试注册的用户名(" 
					+ user.getUsername() 
					+ ")已经被占用！");
			
		}
			//null表示未找到 可以注册
			User user1 = insert(user);
			System.out.println(22222);
			return user1;
		
	}
	
	
	public User login(String username, String password) {
		User result = userMapper.findUserByUsername(username);
		System.out.println("login result:"+result);
		
		if(result !=null) {
			//获取salt和password
			String salt = result.getSalt();
			String md5Password = result.getPassword();
			//进行md5加密
			String md5Re = getEncrpytedPassword(password, salt);
			//与数据库中的password进行比对
			if(!md5Re.equals(md5Password)) {
				System.out.println("''''''''''''''''''''");
				System.out.println(md5Re);
				System.out.println(md5Password);
				throw new PasswordExpiredException("您输入的密码有误！请仔细核对！");
			} 
			//返回数据
			System.out.println("登陆成功！");
			return result;
		} else {
			//用户名不存在
			throw new usernameNotFindException("您尝试的用户名:"+username+"不存在！");
		}
		
	}

	
	
	
	
	private User insert(User user) {
		//1. 生成随机盐，并封装到user中
		String salt = UUID.randomUUID().toString().toUpperCase();
		user.setSalt(salt);
		//2.取出user中原密码执行加密，并封装回user中
		String password = user.getPassword();
		String md5Pwd = getEncrpytedPassword(password, salt);
		user.setPassword(md5Pwd);
		//3. 设置isDelete为0
		user.setIs_delete(0);
		//4. 日志的4项
		Date now = new Date();
		user.setCreate_user(user.getUsername());
		user.setCreate_time(now);
		user.setModified_user(user.getUsername());
		user.setModified_time(now);
		
		
		Integer row = userMapper.insert(user);
		if(row==1) {
			return user;
		}else {
		   throw new InsertDataException("未知错误");
		}
		
		
	}
	
	private String getEncrpytedPassword(String password,String salt) {
		//将原密码加密
		String pwdMd5 = DigestUtils.md5Hex(password).toUpperCase();
		//将盐加密
		String saltMd5 = DigestUtils.md5Hex(salt).toUpperCase();
		//拼接
		String result = pwdMd5+saltMd5;
		//循环家秘密
		for (int i = 0; i < 5; i++) {
			result = DigestUtils.md5Hex(result).toUpperCase();
		}
		
		return result;
	}







	
	
	
}
