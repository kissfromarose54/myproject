package test;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class getconn {
	
	@Test
	public void getConn() throws SQLException {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
	    BasicDataSource dataSource = ac.getBean("dataSource", BasicDataSource.class);
	    
	    Connection conn = dataSource.getConnection();
	    System.out.println(conn);
	
	
	}
}
