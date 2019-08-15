package cn.cmh.myproject.util;

public final class TextValidator {
	
     //私有化构造方法 不希望被调用
	private TextValidator() {
		super();
	}
	
	/**
	 * 验证username的格式正则
	 */
	private static final String REGEX_USERNAME
	      = "[a-zA-Z]{1}[a-zA-Z0-9_]{3,15}";
	
	
	/**
	 * 验证电话号码的正则
	 */
	private static final String REGEX_PHONE
	      = "[0-9]{11}";
	
	
	private static final String REGEX_EMAIL
	      = "^[a-zA-Z0-9]+[@]{1}+[a-zA-Z]+[.]+[a-z]+$";
	
	/**
	 * 验证用户名
	 * @param username
	 * @return
	 */
	public static boolean checkUsername(String username) {
		if(username == null) {
			return false;
		}
		return username.matches(REGEX_USERNAME);
	}
	
	/**
	 * 检查密码长度是否达到要求
	 * @param password
	 * @return
	 */
	public static boolean checkPassword(String password) {
		if(password == null) {
			return false;
		}
		return password.length() >=4 && password.length()<=16;
	}
	
	/**
	 * 检查电话号码
	 * @param username
	 * @return
	 */
	public static boolean checkPhone(String phone) {
		if(phone == null) {
			return false;
		}
		return phone.matches(REGEX_PHONE);
	}
	
	/**
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean checkEmail(String email) {
		if(email == null) {
			return false;
		}
		return email.matches(REGEX_EMAIL);
	}
	
}
















