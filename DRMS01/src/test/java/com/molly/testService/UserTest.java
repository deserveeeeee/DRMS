package com.molly.testService;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.molly.bean.UserBean;
import com.molly.service.IUserService;
import com.molly.util.PageUtil;

public class UserTest {
	
	
	@Test
	public void register(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IUserService service = ac.getBean(IUserService.class);
		UserBean userBean = new UserBean();
		userBean.setUname("Collins");
		userBean.setUphone("18078861993");
		userBean.setUpwd("123456");
		System.out.println(service.register(userBean));
	}
	
	
	@Test
	public void show(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IUserService service = ac.getBean(IUserService.class);
		PageUtil pageUtil = new PageUtil();
		pageUtil.setThisPage(1);
		System.out.println(service.showThisUsers(pageUtil));
	}
	
	@Test
	public void delete(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IUserService service = ac.getBean(IUserService.class);
		UserBean userBean = new UserBean();
		userBean.setUid(13);
		System.out.println(service.deleteUser(userBean));
	}
	
	@Test
	public void change(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IUserService service = ac.getBean(IUserService.class);
		UserBean userBean = new UserBean();
		userBean.setUid(10);
		userBean.setRoles("dataadmin");
		System.out.println(service.setAuth(userBean));
	}
	
	
	@Test
	public void search(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IUserService service = ac.getBean(IUserService.class);
		UserBean userBean = new UserBean();
		PageUtil pageUtil = new PageUtil();
		pageUtil.setThisPage(1);
		userBean.setUname("m");
		System.out.println(service.searchUsers(userBean, pageUtil));
	}
}
