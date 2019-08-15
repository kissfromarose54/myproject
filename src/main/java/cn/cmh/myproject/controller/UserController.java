package cn.cmh.myproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cmh.myproject.entity.ResponseResult;
import cn.cmh.myproject.entity.User;
import cn.cmh.myproject.service.IUserService;
import cn.cmh.myproject.util.TextValidator;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/handle_reg.do",
			        method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handlerReg(User user) {
		//��֤�û��� ���� ���� �ֻ��Ÿ�ʽ
		if(!TextValidator.checkUsername(user.getUsername())) {
			return new ResponseResult<Void>(301,"�û�����ʽ����");
		}
		if(!TextValidator.checkPassword(user.getPassword())) {
			return new ResponseResult<Void>(301,"�����ʽ����");
		}
		if(!TextValidator.checkEmail(user.getEmail())) {
			return new ResponseResult<Void>(301,"�����ʽ����");
		}
		if(!TextValidator.checkPhone(user.getPhone())) {
			return new ResponseResult<Void>(301,"�ֻ��Ÿ�ʽ����");
		}
		
		userService.reg(user);
		return new ResponseResult<Void>();
	}
	
	
	@RequestMapping(value = "/handle_login.do"
			       ,method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<User> handlerLogin(@RequestParam String username,
			@RequestParam String password,
			HttpSession session) {
		/**
		 * ��֤��¼���û������������ȷ��
		 */
		if(!TextValidator.checkUsername(username)) {
			return new ResponseResult<User>(302,"��¼�û�����ʽ����");
		}
		if(!TextValidator.checkPassword(password)) {
			return new ResponseResult<User>(302, "��¼�����ʽ����");
		}
		User user = userService.login(username, password);
		session.setAttribute("uid", user.getUid());
		session.setAttribute("username", user.getUsername());
		return new ResponseResult<User>();
	}
}
