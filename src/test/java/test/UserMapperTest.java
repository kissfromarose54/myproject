package test;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.cmh.myproject.entity.User;
import cn.cmh.myproject.mapper.UserMapper;

public class UserMapperTest {
	
	private ClassPathXmlApplicationContext ac;
	private UserMapper userMapper;
	
	@Test
	public void insert() {
		Date date = new Date();
		User user = new User();
		user.setUsername("jinyn1");
		user.setPassword("112233");
		user.setPhone("13529033461");
		user.setEmail("cmh@tedu.cn");
		user.setGender(1);
		user.setCreate_time(date);
		
		
		Integer row = userMapper.insert(user);
		System.out.println(row);
	}
	
	@Test
	public void find() {
		String username = "lalala";
		User user = userMapper.findUserByUsername(username);
		System.out.println(user);
	}
	
	
	@Before
	public void doBefore() {
		ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		userMapper = ac.getBean("userMapper", UserMapper.class);
	}
	
	@After
	public void doAfter() {
		ac.close();
	}
	
	
	
}
