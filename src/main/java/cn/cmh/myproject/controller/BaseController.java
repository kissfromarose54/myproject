package cn.cmh.myproject.controller;
/**
 * ͳһ�쳣����
 * @author TEDU
 *
 */

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cmh.myproject.entity.ResponseResult;
import cn.cmh.myproject.service.ex.InsertDataException;
import cn.cmh.myproject.service.ex.PasswordNotMatchException;
import cn.cmh.myproject.service.ex.ServiceException;
import cn.cmh.myproject.service.ex.UsernameConflictException;
import cn.cmh.myproject.service.ex.usernameNotFindException;

public class BaseController {
	
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public ResponseResult<Void> handleException(Exception e) {
		if(e instanceof usernameNotFindException) {
			//301 �û���������
			return new ResponseResult<Void>(301,e);
		} else if(e instanceof PasswordNotMatchException) {
			//401 �������
			return new ResponseResult<Void>(401, e);
		} else if(e instanceof UsernameConflictException) {
			//302 �û�����ͻ
			return new ResponseResult<Void>(302, e);
		} else if(e instanceof InsertDataException) {
			//405 ���ݲ������
			return new ResponseResult<Void>(405, e);
		}
		return null;

	}
}
