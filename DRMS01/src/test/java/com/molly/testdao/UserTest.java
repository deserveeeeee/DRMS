package com.molly.testdao;

import javax.sound.midi.VoiceStatus;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.molly.bean.UserBean;
import com.molly.dao.IUserDao;
import com.molly.util.Page;
import com.molly.util.PageUtil;

public class UserTest {
	
	
	@Test
	public void register(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IUserDao dao = ac.getBean(IUserDao.class);
		UserBean userBean = new UserBean();
//		userBean.setRoles("dataadmin");
		userBean.setUname("Tom");
		userBean.setUphone("15884112888");
		userBean.setUpwd("811845989a9e19b2878984d3d86a3c3e");
		System.out.println(dao.insertUserBean(userBean));
//		System.out.println(dao.findByUname(userBean.getUname()));
//		PageUtil pageUtil = new PageUtil();
//		pageUtil.setIndex(0);
//		pageUtil.setPageSize(10);
//		System.out.println(dao.showAllUsers(pageUtil));
		userBean.setUid(5);
		userBean.setRoles("roomadmin");
//		System.out.println(dao.updateUser(userBean));
//		System.out.println(dao.deleteUser(userBean));
		System.out.println();
	}
	
	
	@Test
	public void research(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IUserDao dao = ac.getBean(IUserDao.class);
		UserBean userBean = new UserBean();
		userBean.setUname("%m%");
		userBean.setRoles("%%");
		PageUtil pageUtil = new PageUtil();
		pageUtil.setIndex(0);
		pageUtil.setPageSize(10);
		System.out.println(dao.findUsersByUser(userBean, pageUtil));
	}
}
