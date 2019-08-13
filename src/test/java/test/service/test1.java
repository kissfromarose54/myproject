package test.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.cmh.myproject.entity.User;
import cn.cmh.myproject.service.IUserService;

public class test1 {
	
	private ClassPathXmlApplicationContext ac;
	private IUserService userService;
	
	@Test
	public void reg() {
		User user = new User();
		user.setUsername("balala3");
		user.setPassword("555544");
		System.out.println("user: "+user);
		User re = userService.reg(user);
		System.out.println(re);
	}
	
	@Before
	public void doBefore() {
		ac = new ClassPathXmlApplicationContext("spring-service.xml","spring-dao.xml");
		userService = ac.getBean("userService", IUserService.class);
	}
	
	@After
	public void doAfter() {
		ac.close();
		
	}
}
