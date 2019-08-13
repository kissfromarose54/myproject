package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class test1 {
	public static void main(String[] args) throws ParseException {
//		Date date = new Date();
//		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String re = sd.format(date);
//		System.out.println(re);
		LocalDateTime lt = LocalDateTime.now();
		
		System.out.println(lt);
	}
}
