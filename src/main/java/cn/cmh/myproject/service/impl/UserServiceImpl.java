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
			throw new UsernameConflictException("����ע����û���(" 
					+ user.getUsername() 
					+ ")�Ѿ���ռ�ã�");
			
		}
			//null��ʾδ�ҵ� ����ע��
			User user1 = insert(user);
			System.out.println(22222);
			return user1;
		
	}
	
	
	public User login(String username, String password) {
		User result = userMapper.findUserByUsername(username);
		System.out.println("login result:"+result);
		
		if(result !=null) {
			//��ȡsalt��password
			String salt = result.getSalt();
			String md5Password = result.getPassword();
			//����md5����
			String md5Re = getEncrpytedPassword(password, salt);
			//�����ݿ��е�password���бȶ�
			if(!md5Re.equals(md5Password)) {
				System.out.println("''''''''''''''''''''");
				System.out.println(md5Re);
				System.out.println(md5Password);
				throw new PasswordExpiredException("�������������������ϸ�˶ԣ�");
			} 
			//��������
			System.out.println("��½�ɹ���");
			return result;
		} else {
			//�û���������
			throw new usernameNotFindException("�����Ե��û���:"+username+"�����ڣ�");
		}
		
	}

	
	
	
	
	private User insert(User user) {
		//1. ��������Σ�����װ��user��
		String salt = UUID.randomUUID().toString().toUpperCase();
		user.setSalt(salt);
		//2.ȡ��user��ԭ����ִ�м��ܣ�����װ��user��
		String password = user.getPassword();
		String md5Pwd = getEncrpytedPassword(password, salt);
		user.setPassword(md5Pwd);
		//3. ����isDeleteΪ0
		user.setIs_delete(0);
		//4. ��־��4��
		Date now = new Date();
		user.setCreate_user(user.getUsername());
		user.setCreate_time(now);
		user.setModified_user(user.getUsername());
		user.setModified_time(now);
		
		
		Integer row = userMapper.insert(user);
		if(row==1) {
			return user;
		}else {
		   throw new InsertDataException("δ֪����");
		}
		
		
	}
	
	private String getEncrpytedPassword(String password,String salt) {
		//��ԭ�������
		String pwdMd5 = DigestUtils.md5Hex(password).toUpperCase();
		//���μ���
		String saltMd5 = DigestUtils.md5Hex(salt).toUpperCase();
		//ƴ��
		String result = pwdMd5+saltMd5;
		//ѭ��������
		for (int i = 0; i < 5; i++) {
			result = DigestUtils.md5Hex(result).toUpperCase();
		}
		
		return result;
	}







	
	
	
}
