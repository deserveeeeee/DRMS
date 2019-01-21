package com.molly.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.molly.bean.UserBean;
import com.molly.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserHandler {
	
	
	@Autowired
	private IUserService userService;
	
	
//	登陆的方法
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(@Validated UserBean bean,BindingResult result){
		if(result.hasErrors()){
			List<FieldError> list = result.getFieldErrors();
			return list.toString();
		}
		String result2 = "";
		//SecurityUtils是shiro提供的工具包，包含了subject的获取
				Subject subject = SecurityUtils.getSubject();
				//判断当前subject是否认证过
				if (!subject.isAuthenticated()) {
					//开始进行认证（登录）操作，将用户名和密码传递给UsernamePasswordToken
					UsernamePasswordToken token = new UsernamePasswordToken(bean.getUname(),bean.getUpwd());
		            try {
		            	//调用login进行认证
		            	subject.login(token);
		                System.out.println("认证成功");
		                return "success";
		                //找不到用户回抛出异常
		            } catch (UnknownAccountException uae) {
		                System.out.println("用户名无法匹配");
		                result2 = "用户名无法匹配";
		                //密码匹配错误异常
		            } catch (IncorrectCredentialsException ice) {
		               System.out.println("密码错误");
		               result2 = "密码错误";
		                //用户被锁异常
		            } catch (LockedAccountException lae) {
		                System.out.println("账户被锁定异常");
		                result2 = "账户被锁定异常";
		            }
		            // 父异常。认证失败异常
		            catch (AuthenticationException ae) {
		                //unexpected condition?  error?
		            	System.out.println("异常不详：自己解决");
		            	result2 = "异常不详：自己解决";
		            }
				}
				return result2;
	}
	
	
//	注册功能
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public String resig(@Validated UserBean bean,BindingResult result){
		if(result.hasErrors()){
			List<FieldError> list = result.getFieldErrors();
			return list.toString();
		}
		String result2 = userService.register(bean);
		return result2;
	}
	
}
