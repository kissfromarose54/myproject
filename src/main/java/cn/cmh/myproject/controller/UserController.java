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
		//验证用户名 密码 邮箱 手机号格式
		if(!TextValidator.checkUsername(user.getUsername())) {
			return new ResponseResult<Void>(301,"用户名格式错误！");
		}
		if(!TextValidator.checkPassword(user.getPassword())) {
			return new ResponseResult<Void>(301,"密码格式错误！");
		}
		if(!TextValidator.checkEmail(user.getEmail())) {
			return new ResponseResult<Void>(301,"邮箱格式错误");
		}
		if(!TextValidator.checkPhone(user.getPhone())) {
			return new ResponseResult<Void>(301,"手机号格式错误");
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
		 * 验证登录的用户名和密码的正确性
		 */
		if(!TextValidator.checkUsername(username)) {
			return new ResponseResult<User>(302,"登录用户名格式错误！");
		}
		if(!TextValidator.checkPassword(password)) {
			return new ResponseResult<User>(302, "登录密码格式错误！");
		}
		User user = userService.login(username, password);
		session.setAttribute("uid", user.getUid());
		session.setAttribute("username", user.getUsername());
		return new ResponseResult<User>();
	}
}
