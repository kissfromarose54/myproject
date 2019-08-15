package cn.cmh.myproject.util;

public final class TextValidator {
	
     //˽�л����췽�� ��ϣ��������
	private TextValidator() {
		super();
	}
	
	/**
	 * ��֤username�ĸ�ʽ����
	 */
	private static final String REGEX_USERNAME
	      = "[a-zA-Z]{1}[a-zA-Z0-9_]{3,15}";
	
	
	/**
	 * ��֤�绰���������
	 */
	private static final String REGEX_PHONE
	      = "[0-9]{11}";
	
	
	private static final String REGEX_EMAIL
	      = "^[a-zA-Z0-9]+[@]{1}+[a-zA-Z]+[.]+[a-z]+$";
	
	/**
	 * ��֤�û���
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
	 * ������볤���Ƿ�ﵽҪ��
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
	 * ���绰����
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
















