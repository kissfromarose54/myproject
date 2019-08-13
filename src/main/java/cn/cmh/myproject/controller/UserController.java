package cn.cmh.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cmh.myproject.entity.ResponseResult;
import cn.cmh.myproject.entity.User;
import cn.cmh.myproject.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/handle_reg.do",
			        method = RequestMethod.GET)
	@ResponseBody
	public ResponseResult<Void> handlerReg(User user) {
		userService.reg(user);
		return new ResponseResult<Void>();
	}
}
