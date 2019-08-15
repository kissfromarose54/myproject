package test;

public class test2 {

	public static void main(String[] args) {
		String uri = "http://localhost:8080/mypro-nike/myweb-nike/nike-index(after-login).html";
		int beginIndex = uri.lastIndexOf("/")+1;
		System.out.println(beginIndex);
		String fileName = uri.substring(beginIndex);
		System.out.println("当前请求页面：" + fileName);

	}

}
